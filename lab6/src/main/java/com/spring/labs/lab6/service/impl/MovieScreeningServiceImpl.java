package com.spring.labs.lab6.service.impl;

import com.spring.labs.lab6.entity.MovieScreening;
import com.spring.labs.lab6.exception.NoScreeningFoundException;
import com.spring.labs.lab6.exception.ScreeningAlreadyExistsException;
import com.spring.labs.lab6.repository.MovieScreeningRepository;
import com.spring.labs.lab6.service.MovieScreeningService;
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
        return movieScreeningRepository.findMovieScreeningByIdUsingNamedQueryAnnotation(id)
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
                screening.getDate(), screening.getCinemaHall())
                .ifPresent(ms -> {
                    throw new ScreeningAlreadyExistsException("Another screening already planned for this date and time!");
                });
        screening.setId(null);
        return movieScreeningRepository.save(screening);
    }

    @Override
    @Transactional
    public MovieScreening updateScreening(MovieScreening newScreening, long id) {
        Optional<MovieScreening> existingScreening = movieScreeningRepository
                .findByDateAndCinemaHall(newScreening.getDate(), newScreening.getCinemaHall());

        if (existingScreening.isPresent() && existingScreening.get().getId() != id) {
            throw new ScreeningAlreadyExistsException("Cannot update screening. Another screening already planned for this date and time!");
        }

        MovieScreening oldScreening = movieScreeningRepository
                .findMovieScreeningByIdUsingQueryAnnotation(id)
                .orElseThrow(() -> new NoScreeningFoundException("No movie screening found with id: " + id + "."));

        oldScreening.setDate(newScreening.getDate());
        oldScreening.setMovieName(newScreening.getMovieName());
        oldScreening.setCinemaHall(newScreening.getCinemaHall());
        movieScreeningRepository.save(oldScreening);
        return oldScreening;
    }

    @Override
    @Transactional
    public void deleteScreeningById(long id) {
        movieScreeningRepository.deleteById(id);
    }
}
