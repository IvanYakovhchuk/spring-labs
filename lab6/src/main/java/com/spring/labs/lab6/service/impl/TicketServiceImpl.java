package com.spring.labs.lab6.service.impl;

import com.spring.labs.lab6.entity.MovieScreening;
import com.spring.labs.lab6.entity.Seat;
import com.spring.labs.lab6.entity.Ticket;
import com.spring.labs.lab6.exception.InvalidSeatForScreeningException;
import com.spring.labs.lab6.exception.NoTicketFoundException;
import com.spring.labs.lab6.exception.TicketAlreadyExistsException;
import com.spring.labs.lab6.exception.TransactionFailedException;
import com.spring.labs.lab6.repository.TicketRepository;
import com.spring.labs.lab6.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;

    @Autowired
    public TicketServiceImpl(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    public Ticket getTicketById(long id) {
        return ticketRepository.findTicketByIdUsingNamedQueryAnnotation(id)
                .orElseThrow(() -> new NoTicketFoundException("No ticket found with id: " + id + "."));
    }

    @Override
    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    @Override
    @Transactional
    public Ticket createTicket(Ticket ticket) {
        checkSeatBelongsToScreeningHall(ticket);
        checkSeatAlreadyBooked(ticket);

        ticket.setId(null);
        return ticketRepository.save(ticket);
    }

    @Override
    @Transactional
    public Ticket createTicket(Ticket ticket, boolean forceFail)  {
        checkSeatBelongsToScreeningHall(ticket);
        checkSeatAlreadyBooked(ticket);

        ticket.setId(null);
        Ticket created = ticketRepository.save(ticket);
        if (forceFail) {
            throw new TransactionFailedException("Transaction was failed, rollback occurred!");
        }
        return created;
    }

    @Override
    @Transactional
    public Ticket updateTicket(Ticket newTicket, long id) {
        checkSeatBelongsToScreeningHall(newTicket);

        Optional<Ticket> existingTicket = ticketRepository
                .findBySeatAndScreening(newTicket.getSeat(), newTicket.getScreening());

        if (existingTicket.isPresent() && existingTicket.get().getId() != id) {
            throw new TicketAlreadyExistsException(buildBookedSeatMessage(newTicket, true));
        }

        Ticket oldTicket = ticketRepository.findTicketByIdUsingQueryAnnotation(id)
                .orElseThrow(() -> new NoTicketFoundException("No ticket found with id: " + id + "."));

        oldTicket.setSeat(newTicket.getSeat());
        oldTicket.setScreening(newTicket.getScreening());
        oldTicket.setCustomerName(newTicket.getCustomerName());
        oldTicket.setPrice(newTicket.getPrice());
        return ticketRepository.save(oldTicket);
    }

    @Override
    @Transactional
    public void deleteTicketById(long id) {
        ticketRepository.deleteById(id);
    }

    private String buildBookedSeatMessage(Ticket ticket) {
        return buildBookedSeatMessage(ticket, false);
    }

    private String buildBookedSeatMessage(Ticket ticket, boolean isUpdate) {
        Seat seat = ticket.getSeat();
        MovieScreening screening = ticket.getScreening();
        String action = isUpdate ? "Cannot update ticket. " : "";
        return String.format(
                "%sSeat %d (row %d) for screening \"%s\" (%s) is already booked!",
                action,
                seat.getNumber(),
                seat.getRow(),
                screening.getMovieName(),
                screening.getDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy-HH.mm"))
        );
    }

    private void checkSeatAlreadyBooked(Ticket ticket) {
        ticketRepository.findBySeatAndScreening(ticket.getSeat(), ticket.getScreening())
                .ifPresent(t -> {
                    throw new TicketAlreadyExistsException(buildBookedSeatMessage(ticket));
                });
    }

    private void checkSeatBelongsToScreeningHall(Ticket ticket) {
        if (ticket.getSeat().getCinemaHall() != ticket.getScreening().getCinemaHall()) {
            throw new InvalidSeatForScreeningException(
                    String.format(
                            "Seat you are trying to book is located in a different cinema hall (%d) than the one screening will be shown in (%d)",
                            ticket.getSeat().getCinemaHall(),
                            ticket.getScreening().getCinemaHall()
                    )
            );
        }
    }
}
