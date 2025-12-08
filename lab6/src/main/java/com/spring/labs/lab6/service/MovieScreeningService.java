package com.spring.labs.lab6.service;

import com.spring.labs.lab6.entity.MovieScreening;

import java.util.List;

public interface MovieScreeningService {
    MovieScreening getScreeningById(long id);
    List<MovieScreening> getAllScreenings();
    MovieScreening createScreening(MovieScreening screening);
    MovieScreening updateScreening(MovieScreening newScreening, long id);
    void deleteScreeningById(long id);
}
