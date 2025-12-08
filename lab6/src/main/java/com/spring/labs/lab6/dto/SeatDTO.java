package com.spring.labs.lab6.dto;

import com.spring.labs.lab6.entity.Seat;

public record SeatDTO(int cinemaHall, int row, int number, boolean vip) {
    public static SeatDTO fromEntity(Seat seat) {
        return new SeatDTO(seat.getCinemaHall(), seat.getRow(), seat.getNumber(), seat.isVip());
    }
}
