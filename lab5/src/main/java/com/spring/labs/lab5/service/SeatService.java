package com.spring.labs.lab5.service;

import com.spring.labs.lab5.entity.Seat;

import java.util.List;

public interface SeatService {
    Seat getSeatById(long id);
    List<Seat> getAllSeats();
    Seat createSeat(Seat seat);
    Seat updateSeat(Seat newSeat, long id);
    void deleteSeatById(long id);
}
