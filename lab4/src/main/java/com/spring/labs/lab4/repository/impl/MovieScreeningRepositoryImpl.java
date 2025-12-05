package com.spring.labs.lab4.repository.impl;

import com.spring.labs.lab4.entity.MovieScreening;
import com.spring.labs.lab4.repository.MovieScreeningRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class MovieScreeningRepositoryImpl implements MovieScreeningRepository {

    private final List<MovieScreening> screenings;

    @Autowired
    public MovieScreeningRepositoryImpl(List<MovieScreening> screenings) {
        this.screenings = screenings;
    }

    @Override
    public Optional<MovieScreening> findById(long id) {
        return screenings.stream()
                .filter(s -> s.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<MovieScreening> findAll() {
        return new ArrayList<>(screenings);
    }

    @Override
    public MovieScreening save(MovieScreening screening) {
        screenings.add(screening);
        return screening;
    }

    @Override
    public MovieScreening update(long id, MovieScreening newScreening) {
        MovieScreening oldScreening = findById(id).orElse(null);
        if (oldScreening != null) {
            oldScreening.setCinemaHall(newScreening.getCinemaHall());
            oldScreening.setDate(newScreening.getDate());
            oldScreening.setMovieName(newScreening.getMovieName());
        }
        return oldScreening;
    }

    @Override
    public boolean delete(long id) {
        return screenings.removeIf(s -> s.getId().equals(id));
    }
}
