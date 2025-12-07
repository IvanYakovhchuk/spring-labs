package com.spring.labs.lab4.controller;

import com.spring.labs.lab4.dto.MovieScreeningDTO;
import com.spring.labs.lab4.service.MovieScreeningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "movieScreenings")
public class MovieScreeningController {

    private final MovieScreeningService movieScreeningService;

    @Autowired
    public MovieScreeningController(MovieScreeningService movieScreeningService) {
        this.movieScreeningService = movieScreeningService;
    }

    @GetMapping("{id}")
    public MovieScreeningDTO getMovieScreening(@PathVariable Long id) {
        var screening = movieScreeningService.getScreeningById(id);
        return new MovieScreeningDTO(
            screening.getId(),
            screening.getDate(),
            screening.getMovieName(),
            screening.getCinemaHall(),
            screening.getBookedSeatsIds().stream().toList()
        );
    }
}
