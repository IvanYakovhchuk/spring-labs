package com.spring.labs.lab4.repository;

import com.spring.labs.lab4.entity.Seat;

import java.util.List;
import java.util.Optional;

public interface SeatRepository {
    Optional<Seat> findById(long id);
    List<Seat> findAll();
    Seat save(Seat seat);
    Seat update(long id, Seat newSeat);
    boolean delete(long id);

}
