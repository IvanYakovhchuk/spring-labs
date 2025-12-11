package com.spring.labs.lab5.repository;

import com.spring.labs.lab5.entity.Seat;

import java.util.List;
import java.util.Optional;

public interface SeatRepository {
    Optional<Seat> findById(long id);
    Optional<Seat> findByCinemaHallAndRowAndNumber(int cinemaHall, int rowNumber, int seatNumber);
    List<Seat> findAll();
    Seat create(Seat seat);
    Seat update(long id, Seat seat);
    void delete(long id);
}
