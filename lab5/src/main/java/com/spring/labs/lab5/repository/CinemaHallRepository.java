package com.spring.labs.lab5.repository;

import com.spring.labs.lab5.entity.CinemaHall;

import java.util.List;
import java.util.Optional;

public interface CinemaHallRepository {
    Optional<CinemaHall> findById(long id);
    List<CinemaHall> findAll();
    long create(CinemaHall hall);
    void update(long id, CinemaHall hall);
    void delete(long id);
}
