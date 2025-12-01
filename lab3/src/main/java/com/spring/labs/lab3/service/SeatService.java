package com.spring.labs.lab3.service;

import com.spring.labs.lab3.entity.Seat;

import java.util.List;

public interface SeatService {
    Seat getSeatById(long id);
    List<Seat> getAllSeats();
    Seat addSeat(Seat seat);
    Seat updateSeatById(long id, Seat newSeat);
    boolean removeSeatById(long id);
    List<Seat> getAllSeatsOfCinemaHall(int cinemaHall);
}
