package com.spring.labs.lab4.controller;

import com.spring.labs.lab4.dto.*;
import com.spring.labs.lab4.service.MovieScreeningService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(path = "movieScreenings")
public class MovieScreeningController {

    private final MovieScreeningService movieScreeningService;

    @Autowired
    public MovieScreeningController(MovieScreeningService movieScreeningService) {
        this.movieScreeningService = movieScreeningService;
    }

    @GetMapping("{id}")
    public MovieScreeningDTO getById(@PathVariable @Positive @NotNull Long id) {
        var screening = movieScreeningService.getScreeningById(id);
        return MovieScreeningDTO.fromEntity(screening);
    }

    @GetMapping
    public Page<MovieScreeningDTO> getAll(
            @Valid FilterMovieScreeningDTO filterDTO,
            @Valid PaginationDTO paginationDTO,
            @Valid SortOrderDTO sortOrderDTO) {
        var allowedFields = List.of("id", "date", "movieName", "cinemaHall");

        if (!allowedFields.contains(sortOrderDTO.getOrderBy())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid sort field");
        }

        var page = movieScreeningService.getAllScreenings(filterDTO, paginationDTO, sortOrderDTO);
        return new Page<MovieScreeningDTO>(
                page.getRequested(),
                page.getPageSize(),
                page.getTotalPages(),
                page.getTotal(),
                page.getItems().stream().map(MovieScreeningDTO::fromEntity).toList());
    }

    @PostMapping
    public MovieScreeningDTO addScreening(@Valid @RequestBody CreateMovieScreeningDTO dto) {
        var screening = movieScreeningService.addScreening(dto);
        return MovieScreeningDTO.fromEntity(screening);
    }
}
