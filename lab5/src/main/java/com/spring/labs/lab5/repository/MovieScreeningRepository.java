package com.spring.labs.lab5.repository;

import com.spring.labs.lab5.entity.MovieScreening;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface MovieScreeningRepository {
    Optional<MovieScreening> findById(long id);
    Optional<MovieScreening> findByDateAndCinemaHall(LocalDateTime screeningDate, int cinemaHall);
    List<MovieScreening> findAll();
    MovieScreening create(MovieScreening screening);
    MovieScreening update(long id, MovieScreening newScreening);
    void delete(long id);
}
