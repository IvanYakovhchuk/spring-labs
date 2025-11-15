package com.spring.labs.lab2.repository;

import com.spring.labs.lab2.entity.MovieScreening;
import com.spring.labs.lab2.entity.Seat;

import java.util.List;
import java.util.Optional;

public interface MovieScreeningRepository {
    Optional<MovieScreening> findById(long id);
    List<MovieScreening> findAll();
    MovieScreening save(MovieScreening screening);
    MovieScreening update(long id, MovieScreening newScreening);
    boolean delete(long id);
}
