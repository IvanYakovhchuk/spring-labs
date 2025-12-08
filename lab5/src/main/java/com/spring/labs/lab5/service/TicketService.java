package com.spring.labs.lab5.service;

import com.spring.labs.lab5.entity.Ticket;

import java.util.List;

public interface TicketService {
    Ticket getTicketById(long id);
    List<Ticket> getAllTickets();
    Ticket createTicket(Ticket ticket);
    Ticket updateTicket(Ticket newTicket, long id);
    void deleteTicketById(long id);
}
