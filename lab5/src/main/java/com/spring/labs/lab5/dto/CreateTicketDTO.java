package com.spring.labs.lab5.dto;

import com.spring.labs.lab5.entity.Ticket;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

@Schema(description = "Data Transfer Object for creating a new Ticket")
public record CreateTicketDTO(
    @Schema(description = "ID of a seat booked in the ticket", example = "1") long seatId,
    @Schema(description = "ID of a screening chosen in the ticket", example = "1") long screeningId,
    @Schema(description = "Customer's name", example = "John Doe") String customerName,
    @Schema(description = "Price of a ticket", example = "150.0") BigDecimal price
) { }

