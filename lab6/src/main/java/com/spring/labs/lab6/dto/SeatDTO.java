package com.spring.labs.lab6.dto;

import com.spring.labs.lab6.entity.Seat;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Data Transfer Object representing a Seat")
public record SeatDTO(
        @Schema(description = "Cinema hall where the seat is located", example = "3") int cinemaHall,
        @Schema(description = "Row of the seat in the cinema hall", example = "7") int row,
        @Schema(description = "Number of the seat in a row", example = "13") int number,
        @Schema(description = "The status of the seat", allowableValues = {"true", "false"}) boolean vip
) {
    public static SeatDTO fromEntity(Seat seat) {
        return new SeatDTO(seat.getCinemaHall(), seat.getRow(), seat.getNumber(), seat.isVip());
    }
}
