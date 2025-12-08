package com.spring.labs.lab5.controller;

import com.spring.labs.lab5.dto.MovieScreeningDTO;
import com.spring.labs.lab5.entity.MovieScreening;
import com.spring.labs.lab5.service.MovieScreeningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/screenings")
public class MovieScreeningController {

    private final MovieScreeningService movieScreeningService;

    @Autowired
    public MovieScreeningController(MovieScreeningService movieScreeningService) {
        this.movieScreeningService = movieScreeningService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieScreeningDTO> getScreening(@PathVariable(name = "id") long id) {
        return ResponseEntity.ok(MovieScreeningDTO.fromEntity(movieScreeningService.getScreeningById(id)));
    }

    @GetMapping
    public ResponseEntity<List<MovieScreeningDTO>> getAllScreenings() {
        return ResponseEntity.ok(movieScreeningService.getAllScreenings().stream()
            .map(MovieScreeningDTO::fromEntity)
            .toList()
        );
    }

    @PostMapping
    public ResponseEntity<MovieScreeningDTO> createScreening(@RequestBody MovieScreeningDTO screening) {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(MovieScreeningDTO.fromEntity(
                movieScreeningService.createScreening(
                new MovieScreening(0, screening.getDate(), screening.getMovieName(), screening.getCinemaHall())
            )));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieScreeningDTO> updateScreening(@PathVariable(name = "id") long id,
                                                             @RequestBody MovieScreeningDTO screening) {
        return ResponseEntity.ok(
            MovieScreeningDTO.fromEntity(
                movieScreeningService.updateScreening(
                    new MovieScreening(0, screening.getDate(), screening.getMovieName(), screening.getCinemaHall()),
                    id
                )
            )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteScreening(@PathVariable(name = "id") long id) {
        movieScreeningService.deleteScreeningById(id);
        return ResponseEntity.noContent().build();
    }
}
