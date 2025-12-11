package com.spring.labs.lab6.service;

import com.spring.labs.lab6.entity.Ticket;

import java.util.List;

public interface TicketService {
    Ticket getTicketById(long id);
    List<Ticket> getAllTickets();
    Ticket createTicket(Ticket ticket);
    Ticket updateTicket(Ticket newTicket, long id);
    void deleteTicketById(long id);
    Ticket createTicket(Ticket ticket, boolean forceFail);
}
