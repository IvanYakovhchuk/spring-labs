package com.spring.labs.lab4.service.impl;

import com.spring.labs.lab4.entity.MovieScreening;
import com.spring.labs.lab4.exception.NoScreeningFound;
import com.spring.labs.lab4.exception.ScreeningAlreadyExists;
import com.spring.labs.lab4.repository.MovieScreeningRepository;
import com.spring.labs.lab4.service.MovieScreeningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class MovieScreeningServiceImpl implements MovieScreeningService {

    @Autowired
    private MovieScreeningRepository movieScreeningRepository;

    public MovieScreening getScreeningById(long id) {
        return movieScreeningRepository.findById(id)
                .orElseThrow(() -> new NoScreeningFound(id));
    }

    public List<MovieScreening> getAllScreenings() {
        return movieScreeningRepository.findAll();
    }

    public MovieScreening addScreening(MovieScreening screening) {
        boolean duplicate = movieScreeningRepository.findAll()
                .stream()
                .anyMatch(s -> s.getId().equals(screening.getId()));

        if (duplicate || movieScreeningExists(screening)) {
            throw new ScreeningAlreadyExists("id or date/hall");
        }

        movieScreeningRepository.save(screening);
        return screening;
    }

    public MovieScreening updateScreeningById(long id, MovieScreening newScreening) {
        MovieScreening oldScreening = movieScreeningRepository.findById(id)
                .orElseThrow(() -> new NoScreeningFound(id));

        boolean duplicate = movieScreeningRepository.findAll().stream()
                .anyMatch(s -> !s.getId().equals(id) &&
                        s.getCinemaHall() == newScreening.getCinemaHall() &&
                        s.getDate().truncatedTo(ChronoUnit.MINUTES)
                                .isEqual(newScreening.getDate().truncatedTo(ChronoUnit.MINUTES)));

        if (duplicate) {
            throw new ScreeningAlreadyExists("date/hall");
        }

        oldScreening.setCinemaHall(newScreening.getCinemaHall());
        oldScreening.setMovieName(newScreening.getMovieName());
        oldScreening.setDate(newScreening.getDate());

        return oldScreening;
    }

    public boolean removeScreeningById(long id) {
        return movieScreeningRepository.delete(id);
    }

    private boolean movieScreeningExists(MovieScreening movieScreening) {
        return movieScreeningRepository.findAll().stream()
                .anyMatch(s -> s.getCinemaHall() == movieScreening.getCinemaHall() &&
                        s.getDate().truncatedTo(ChronoUnit.MINUTES)
                                .isEqual(movieScreening.getDate().truncatedTo(ChronoUnit.MINUTES)));
    }
}
