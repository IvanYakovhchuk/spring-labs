package com.spring.labs.lab6.dto;

import com.spring.labs.lab6.entity.Ticket;

public record TicketDTO(SeatDTO seat, MovieScreeningDTO screening,
                        String customerName, double price) {
    public static TicketDTO fromEntity(Ticket ticket) {
        return new TicketDTO(
                SeatDTO.fromEntity(ticket.getSeat()),
                MovieScreeningDTO.fromEntity(ticket.getScreening()),
                ticket.getCustomerName(),
                ticket.getPrice()
        );
    }
}
