package com.spring.labs.lab5.repository;

import com.spring.labs.lab5.entity.Ticket;

import java.util.List;
import java.util.Optional;

public interface TicketRepository {
    Optional<Ticket> findById(long id);
    List<Ticket> findAll();
    long create(Ticket ticket);
    void update(long id, Ticket ticket);
    void delete(long id);
}
