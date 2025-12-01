package com.spring.labs.lab3.repository;

import com.spring.labs.lab3.entity.MovieScreening;
import com.spring.labs.lab3.entity.Ticket;

import java.util.List;
import java.util.Optional;

public interface TicketRepository {
    Optional<Ticket> findById(long id);
    List<Ticket> findAll();
    Ticket save(Ticket ticket);
    Ticket update(long id, Ticket newTicket);
    boolean delete(long id);
}
