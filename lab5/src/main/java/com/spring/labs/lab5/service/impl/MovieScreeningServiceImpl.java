package com.spring.labs.lab5.service.impl;

import com.spring.labs.lab5.entity.MovieScreening;
import com.spring.labs.lab5.exception.NoScreeningFoundException;
import com.spring.labs.lab5.exception.ScreeningAlreadyExistsException;
import com.spring.labs.lab5.repository.MovieScreeningRepository;
import com.spring.labs.lab5.service.MovieScreeningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MovieScreeningServiceImpl implements MovieScreeningService {

    private final MovieScreeningRepository movieScreeningRepository;

    @Autowired
    public MovieScreeningServiceImpl(MovieScreeningRepository movieScreeningRepository) {
        this.movieScreeningRepository = movieScreeningRepository;
    }

    @Override
    public MovieScreening getScreeningById(long id) {
        return movieScreeningRepository.findById(id)
                .orElseThrow(() -> new NoScreeningFoundException("No movie screening found with id: " + id + "."));
    }

    @Override
    public List<MovieScreening> getAllScreenings() {
        return movieScreeningRepository.findAll();
    }

    @Override
    @Transactional
    public MovieScreening createScreening(MovieScreening screening) {
        movieScreeningRepository.findByDateAndCinemaHall(
                screening.getScreeningDate(), screening.getCinemaHall())
                .ifPresent(ms -> {
                    throw new ScreeningAlreadyExistsException("Another screening already planned for this date and time!");
                });
        return movieScreeningRepository.create(screening);
    }

    @Override
    @Transactional
    public MovieScreening updateScreening(MovieScreening newScreening, long id) {
        Optional<MovieScreening> existingScreening = movieScreeningRepository
                .findByDateAndCinemaHall(newScreening.getScreeningDate(), newScreening.getCinemaHall());

        if (existingScreening.isPresent() && existingScreening.get().getId() != id) {
            throw new ScreeningAlreadyExistsException("Cannot update screening. Another screening already planned for this date and time!");
        }

        MovieScreening oldScreening = movieScreeningRepository
                .findById(id)
                .orElseThrow(() -> new NoScreeningFoundException("No movie screening found with id: " + id + "."));

        return movieScreeningRepository.update(id, newScreening);
    }

    @Override
    @Transactional
    public void deleteScreeningById(long id) {
        movieScreeningRepository.delete(id);
    }
}
