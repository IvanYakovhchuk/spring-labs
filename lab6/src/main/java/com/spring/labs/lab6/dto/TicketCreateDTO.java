package com.spring.labs.lab6.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Data Transfer Object for creating a new Ticket")
public record TicketCreateDTO(
        @Schema(description = "ID of a seat booked in the ticket", example = "1") Long seatId,
        @Schema(description = "ID of a screening chosen in the ticket", example = "1") Long screeningId,
        @Schema(description = "Customer's name", example = "John Doe") String customerName,
        @Schema(description = "Price of a ticket", example = "150.0") double price
) { }
