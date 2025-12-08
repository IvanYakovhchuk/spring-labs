package com.spring.labs.lab5.repository;

import com.spring.labs.lab5.entity.MovieScreening;

import java.util.List;
import java.util.Optional;

public interface MovieScreeningRepository {
    Optional<MovieScreening> findById(long id);
    List<MovieScreening> findAll();
    long create(MovieScreening screening);
    void update(long id, MovieScreening newScreening);
    void delete(long id);
}
