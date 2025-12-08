package com.spring.labs.lab4.service.impl;

import com.spring.labs.lab4.dto.*;
import com.spring.labs.lab4.entity.MovieScreening;
import com.spring.labs.lab4.exception.NoScreeningFound;
import com.spring.labs.lab4.exception.ScreeningAlreadyExists;
import com.spring.labs.lab4.repository.MovieScreeningRepository;
import com.spring.labs.lab4.service.MovieScreeningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;

@Service
public class MovieScreeningServiceImpl implements MovieScreeningService {

    @Autowired
    private MovieScreeningRepository movieScreeningRepository;

    public MovieScreening getScreeningById(long id) {
        return movieScreeningRepository.findById(id)
                .orElseThrow(() -> new NoScreeningFound(id));
    }

    @Override
    public Page<MovieScreening> getAllScreenings(
            FilterMovieScreeningDTO filterDTO,
            PaginationDTO paginationDTO,
            SortOrderDTO sortOrderDTO) {
        return movieScreeningRepository.findAll(filterDTO, paginationDTO, sortOrderDTO);
    }

    public MovieScreening addScreening(CreateMovieScreeningDTO dto) {
        var screening = dto.toEntity();

        if (movieScreeningExists(screening) || movieScreeningExists(screening)) {
            throw new ScreeningAlreadyExists("id or date/hall");
        }

        movieScreeningRepository.save(screening);
        return screening;
    }

    public MovieScreening updateScreeningById(long id, UpdateMovieScreeningDTO dto) {
        MovieScreening oldScreening = movieScreeningRepository.findById(id)
                .orElseThrow(() -> new NoScreeningFound(id));

        MovieScreening newScreening = new MovieScreening(oldScreening);
        dto.updateEntity(newScreening);

        if (movieScreeningCollides(newScreening)) {
            throw new ScreeningAlreadyExists("date/hall");
        }

        return movieScreeningRepository.updateById(id, dto);
    }

    public boolean removeScreeningById(long id) {
        return movieScreeningRepository.delete(id);
    }

    private boolean movieScreeningExists(MovieScreening movieScreening) {
        return movieScreeningRepository.findById(movieScreening.getId()).isPresent();
    }

    private boolean movieScreeningCollides(MovieScreening movieScreening) {
        return movieScreeningRepository.findAll().stream()
                .anyMatch(s -> screeningsCollide(s, movieScreening));
    }

    private boolean screeningsCollide(MovieScreening s1, MovieScreening s2) {
        return !s1.getId().equals(s2.getId())
                && s1.getCinemaHall() == s2.getCinemaHall()
                && s1.getDate().truncatedTo(ChronoUnit.MINUTES)
                    .isEqual(s2.getDate().truncatedTo(ChronoUnit.MINUTES));
    }
}
