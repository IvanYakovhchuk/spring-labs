package com.spring.labs.lab5.service.impl;

import com.spring.labs.lab5.entity.MovieScreening;
import com.spring.labs.lab5.entity.Seat;
import com.spring.labs.lab5.entity.Ticket;
import com.spring.labs.lab5.exception.*;
import com.spring.labs.lab5.repository.MovieScreeningRepository;
import com.spring.labs.lab5.repository.SeatRepository;
import com.spring.labs.lab5.repository.TicketRepository;
import com.spring.labs.lab5.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final MovieScreeningRepository screeningRepository;
    private final SeatRepository seatRepository;

    @Autowired
    public TicketServiceImpl(TicketRepository ticketRepository,
                             MovieScreeningRepository screeningRepository,
                             SeatRepository seatRepository) {
        this.ticketRepository = ticketRepository;
        this.screeningRepository = screeningRepository;
        this.seatRepository = seatRepository;
    }

    public Ticket getTicketById(long id) {
        return ticketRepository.findById(id)
                .orElseThrow(() -> new NoTicketFound(id));
    }

    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    public Ticket addTicket(Ticket ticket) {
        MovieScreening screening = screeningRepository.findById(ticket.getScreeningId())
                .orElseThrow(NoScreeningFound::new);

        Seat seat = seatRepository.findById(ticket.getSeatId())
                .orElseThrow(NoSeatFound::new);

        if (screening.getCinemaHall() != seat.getCinemaHall()) {
            throw new InvalidSeatForScreening();
        }

        boolean duplicate = ticketRepository.findAll().stream()
                .anyMatch(t -> t.getId().equals(ticket.getId()) ||
                        (t.getScreeningId().equals(ticket.getScreeningId()) &&
                         t.getSeatId().equals(ticket.getSeatId())));

        if (duplicate) {
            throw new TicketAlreadyExists("seat and screening");
        }

        long nextId = ticketRepository.findAll().stream()
                .mapToLong(Ticket::getId)
                .max()
                .orElse(0) + 1;

        ticket.setId(nextId);

        ticketRepository.save(ticket);
        screening.getBookedSeatsIds().add(ticket.getSeatId());
        return ticket;
    }

    public Ticket updateTicketById(long id, Ticket newTicket) {
        Ticket oldTicket = ticketRepository.findById(id)
                .orElseThrow(() -> new NoTicketFound(id));

        if (isSeatAndScreeningInvalid(newTicket))
            throw new InvalidSeatForScreening();

        boolean duplicate = ticketRepository.findAll().stream()
                .anyMatch(t -> !t.getId().equals(id) &&
                        t.getScreeningId().equals(newTicket.getScreeningId()) &&
                        t.getSeatId().equals(newTicket.getSeatId()));


        if (duplicate) {
            throw new TicketAlreadyExists("seat and screening");
        }

        oldTicket.setScreeningId(newTicket.getScreeningId());
        oldTicket.setSeatId(newTicket.getSeatId());
        oldTicket.setCustomerName(newTicket.getCustomerName());
        oldTicket.setPrice(newTicket.getPrice());

        return oldTicket;
    }

    public boolean removeTicketById(long id) {
        return ticketRepository.delete(id);
    }

    private boolean isSeatAndScreeningInvalid(Ticket ticket) {
        MovieScreening screening = screeningRepository.findById(ticket.getScreeningId())
                .orElseThrow(NoScreeningFound::new);

        Seat seat = seatRepository.findById(ticket.getSeatId())
                .orElseThrow(NoSeatFound::new);

        return seat.getCinemaHall() != screening.getCinemaHall();
    }

}
