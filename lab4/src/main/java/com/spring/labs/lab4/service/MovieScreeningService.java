package com.spring.labs.lab4.service;

import com.spring.labs.lab4.entity.MovieScreening;

import java.util.List;

public interface MovieScreeningService {
    MovieScreening getScreeningById(long id);
    List<MovieScreening> getAllScreenings();
    MovieScreening addScreening(MovieScreening screening);
    MovieScreening updateScreeningById(long id, MovieScreening newScreening);
    boolean removeScreeningById(long id);
}
