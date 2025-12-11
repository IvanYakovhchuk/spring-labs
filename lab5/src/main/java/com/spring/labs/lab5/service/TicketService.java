package com.spring.labs.lab5.service;

import com.spring.labs.lab5.dto.TicketDTO;
import com.spring.labs.lab5.entity.Ticket;

import java.util.List;

public interface TicketService {
    TicketDTO getTicketById(long id);
    List<TicketDTO> getAllTickets();
    TicketDTO createTicket(Ticket ticket);
    TicketDTO updateTicket(Ticket newTicket, long id);
    void deleteTicketById(long id);
}
