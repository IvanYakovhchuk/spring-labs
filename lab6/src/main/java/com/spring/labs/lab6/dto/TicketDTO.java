package com.spring.labs.lab6.dto;

import com.spring.labs.lab6.entity.Ticket;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Data Transfer Object representing a Ticket")
public record TicketDTO(
        SeatDTO seat,
        MovieScreeningDTO screening,
        @Schema(description = "Customer's name", example = "John Doe") String customerName,
        @Schema(description = "Price of a ticket", example = "150.0") double price
) {
    public static TicketDTO fromEntity(Ticket ticket) {
        return new TicketDTO(
                SeatDTO.fromEntity(ticket.getSeat()),
                MovieScreeningDTO.fromEntity(ticket.getScreening()),
                ticket.getCustomerName(),
                ticket.getPrice()
        );
    }
}
