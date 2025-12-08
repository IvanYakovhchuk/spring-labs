package com.spring.labs.lab5.service;

import com.spring.labs.lab5.entity.Ticket;

import java.util.List;

public interface TicketService {
    Ticket getTicketById(long id);
    List<Ticket> getAllTickets();
    Ticket addTicket(Ticket ticket);
    Ticket updateTicketById(long id, Ticket newTicket);
    boolean removeTicketById(long id);
}
