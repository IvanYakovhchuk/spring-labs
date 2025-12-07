package com.spring.labs.lab4.controller;

import com.spring.labs.lab4.dto.MovieScreeningDTO;
import com.spring.labs.lab4.service.MovieScreeningService;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "movieScreenings")
public class MovieScreeningController {

    private final MovieScreeningService movieScreeningService;

    @Autowired
    public MovieScreeningController(MovieScreeningService movieScreeningService) {
        this.movieScreeningService = movieScreeningService;
    }

    @GetMapping("{id}")
    public MovieScreeningDTO getMovieScreening(@PathVariable @Positive Long id) {
        var screening = movieScreeningService.getScreeningById(id);
        return MovieScreeningDTO.fromEntity(screening);
    }
}
