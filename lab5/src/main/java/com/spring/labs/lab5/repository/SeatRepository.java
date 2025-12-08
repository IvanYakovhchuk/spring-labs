package com.spring.labs.lab5.repository;

import com.spring.labs.lab5.entity.Seat;

import java.util.List;
import java.util.Optional;

public interface SeatRepository {
    Optional<Seat> findById(long id);
    List<Seat> findAll();
    long create(Seat seat);
    void update(long id, Seat seat);
    void delete(long id);
}
