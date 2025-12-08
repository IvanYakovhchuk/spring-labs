package com.spring.labs.lab4.controller;

import com.spring.labs.lab4.dto.*;
import com.spring.labs.lab4.entity.MovieScreening;
import com.spring.labs.lab4.exception.NoScreeningFound;
import com.spring.labs.lab4.service.MovieScreeningService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Tag(name = "Movie Screening Controller", description = "Endpoints for managing movie screenings")
@RestController
@RequestMapping(path = "movieScreenings")
public class MovieScreeningController {

    private final MovieScreeningService movieScreeningService;

    @Autowired
    public MovieScreeningController(MovieScreeningService movieScreeningService) {
        this.movieScreeningService = movieScreeningService;
    }

    @Operation(summary = "Get movie screening", description = "Get movie screening by id")
    @GetMapping("{id}")
    public MovieScreeningDTO getById(@PathVariable @Positive @NotNull Long id) {
        var screening = movieScreeningService.getScreeningById(id);
        return MovieScreeningDTO.fromEntity(screening);
    }

    @Operation(summary = "Get all movie screenings", description = "Get all movie screenings with given filters, page and ordering")
    @GetMapping
    public Page<MovieScreeningDTO> getAll(
            @ParameterObject @Valid FilterMovieScreeningDTO filterDTO,
            @ParameterObject @Valid PaginationDTO paginationDTO,
            @ParameterObject @Valid SortMovieScreeningDTO sortDTO) {
        var allowedFields = MovieScreening.getSortFields();

        if (!allowedFields.contains(sortDTO.getOrderBy())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid sort field");
        }

        var page = movieScreeningService.getAllScreenings(filterDTO, paginationDTO, sortDTO);
        return new Page<MovieScreeningDTO>(
                page.getRequested(),
                page.getPageSize(),
                page.getTotalPages(),
                page.getTotal(),
                page.getItems().stream().map(MovieScreeningDTO::fromEntity).toList());
    }

    @Operation(summary = "Create movie screening", description = "Create a movie screening if it doesn't collide with others")
    @PostMapping
    public MovieScreeningDTO addScreening(@Valid @RequestBody CreateMovieScreeningDTO dto) {
        var screening = movieScreeningService.addScreening(dto);
        return MovieScreeningDTO.fromEntity(screening);
    }

    @Operation(summary = "Delete movie screening", description = "Delete movie screening by id if it exists")
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteScreening(@PathVariable @Positive @NotNull Long id) {
        var deleted = movieScreeningService.removeScreeningById(id);
        if (!deleted) {
            throw new NoScreeningFound();
        }
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Update movie screening", description = "Partially update movie screening by id")
    @PatchMapping("{id}")
    public MovieScreeningDTO updateScreening(
            @PathVariable @Positive @NotNull Long id,
            @Valid @RequestBody UpdateMovieScreeningDTO dto) {
        var screening = movieScreeningService.updateScreeningById(id, dto);
        return MovieScreeningDTO.fromEntity(screening);
    }
}
