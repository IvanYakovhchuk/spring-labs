package com.spring.labs.lab5.service.impl;

import com.spring.labs.lab5.entity.MovieScreening;
import com.spring.labs.lab5.entity.Seat;
import com.spring.labs.lab5.entity.Ticket;
import com.spring.labs.lab5.exception.InvalidSeatForScreeningException;
import com.spring.labs.lab5.exception.NoTicketFoundException;
import com.spring.labs.lab5.exception.TicketAlreadyExistsException;
import com.spring.labs.lab5.repository.TicketRepository;
import com.spring.labs.lab5.service.MovieScreeningService;
import com.spring.labs.lab5.service.SeatService;
import com.spring.labs.lab5.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final SeatService seatService;
    private final MovieScreeningService movieScreeningService;

    @Autowired
    public TicketServiceImpl(
        TicketRepository ticketRepository,
        SeatService seatService,
        MovieScreeningService movieScreeningService
    ) {
        this.ticketRepository = ticketRepository;
        this.seatService = seatService;
        this.movieScreeningService = movieScreeningService;
    }

    @Override
    public Ticket getTicketById(long id) {
        return ticketRepository.findById(id)
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

        return ticketRepository.create(ticket);
    }

    @Override
    @Transactional
    public Ticket updateTicket(Ticket newTicket, long id) {
        checkSeatBelongsToScreeningHall(newTicket);

        Optional<Ticket> existingTicket = ticketRepository
                .findBySeatAndScreening(newTicket.getSeatId(), newTicket.getScreeningId());

        if (existingTicket.isPresent() && existingTicket.get().getId() != id) {
            throw new TicketAlreadyExistsException(buildBookedSeatMessage(newTicket, true));
        }

        Ticket oldTicket = ticketRepository.findById(id)
                .orElseThrow(() -> new NoTicketFoundException("No ticket found with id: " + id + "."));

        return ticketRepository.update(id, newTicket);
    }

    @Override
    @Transactional
    public void deleteTicketById(long id) {
        ticketRepository.delete(id);
    }

    private String buildBookedSeatMessage(Ticket ticket) {
        return buildBookedSeatMessage(ticket, false);
    }

    private String buildBookedSeatMessage(Ticket ticket, boolean isUpdate) {
        Seat seat = seatService.getSeatById(ticket.getSeatId());
        MovieScreening screening = movieScreeningService.getScreeningById(ticket.getScreeningId());
        String action = isUpdate ? "Cannot update ticket. " : "";
        return String.format(
                "%sSeat %d (row %d) for screening \"%s\" (%s) is already booked!",
                action,
                seat.getCinemaHall(),
                seat.getRowNumber(),
                screening.getMovieName(),
                screening.getScreeningDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy-HH.mm"))
        );
    }

    private void checkSeatAlreadyBooked(Ticket ticket) {
        ticketRepository.findBySeatAndScreening(ticket.getSeatId(), ticket.getScreeningId())
                .ifPresent(t -> {
                    throw new TicketAlreadyExistsException(buildBookedSeatMessage(ticket));
                });
    }

    private void checkSeatBelongsToScreeningHall(Ticket ticket) {
        Seat seat = seatService.getSeatById(ticket.getSeatId());
        MovieScreening screening = movieScreeningService.getScreeningById(ticket.getScreeningId());
        if (seat.getCinemaHall() != screening.getCinemaHall()) {
            throw new InvalidSeatForScreeningException(
                    String.format(
                            "Seat you are trying to book is located in a different cinema hall (%d) than the one screening will be shown in (%d)",
                            seat.getCinemaHall(),
                            screening.getCinemaHall()
                    )
            );
        }
    }
}
