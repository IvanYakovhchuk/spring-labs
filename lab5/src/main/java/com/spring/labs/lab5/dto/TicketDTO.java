package com.spring.labs.lab5.dto;

import com.spring.labs.lab5.entity.MovieScreening;
import com.spring.labs.lab5.entity.Seat;
import com.spring.labs.lab5.entity.Ticket;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

@Schema(description = "Data Transfer Object representing a Ticket")
public record TicketDTO(
    SeatDTO seat,
    MovieScreeningDTO screening,
    @Schema(description = "Customer's name", example = "John Doe") String customerName,
    @Schema(description = "Price of a ticket", example = "150.0") BigDecimal price
) {
    public static TicketDTO fromEntity(Ticket ticket, Seat seat, MovieScreening screening) {
        return new TicketDTO(
            SeatDTO.fromEntity(seat),
            MovieScreeningDTO.fromEntity(screening),
            ticket.getCustomerName(),
            ticket.getPrice()
        );
    }
}
