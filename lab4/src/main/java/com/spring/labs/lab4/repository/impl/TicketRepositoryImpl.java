package com.spring.labs.lab4.repository.impl;

import com.spring.labs.lab4.entity.Ticket;
import com.spring.labs.lab4.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class TicketRepositoryImpl implements TicketRepository {

    private final List<Ticket> tickets;

    @Autowired
    public TicketRepositoryImpl(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    @Override
    public Optional<Ticket> findById(long id) {
        return tickets.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Ticket> findAll() {
        return new ArrayList<>(tickets);
    }

    @Override
    public Ticket save(Ticket ticket) {
        tickets.add(ticket);
        return ticket;
    }

    @Override
    public Ticket update(long id, Ticket newTicket) {
        Ticket oldTicket = findById(id).orElse(null);
        if (oldTicket != null) {
            oldTicket.setScreeningId(newTicket.getScreeningId());
            oldTicket.setSeatId(newTicket.getSeatId());
            oldTicket.setCustomerName(newTicket.getCustomerName());
            oldTicket.setPrice(newTicket.getPrice());
        }
        return oldTicket;
    }

    @Override
    public boolean delete(long id) {
        return tickets.removeIf(t -> t.getId().equals(id));
    }
}
