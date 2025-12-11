package com.spring.labs.lab5.dto;

import com.spring.labs.lab5.entity.Seat;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Data Transfer Object representing a seat")
public record SeatDTO(
    @Schema(description = "Seat id")
    long id,
    @Schema(description = "Cinema hall where the seat is located")
    int cinemaHall,
    @Schema(description = "Row of the seat in the cinema hall")
    int rowNumber,
    @Schema(description = "Number of the seat in a row")
    int seatNumber,
    @Schema(description = "VIP status of the seat")
    boolean vip
) {
    public static SeatDTO fromEntity(Seat seat) {
        return new SeatDTO(
            seat.getId(),
            seat.getCinemaHall(),
            seat.getRowNumber(),
            seat.getSeatNumber(),
            seat.getIsVip()
        );
    }
}
