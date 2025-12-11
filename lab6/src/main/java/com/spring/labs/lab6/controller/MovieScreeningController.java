package com.spring.labs.lab6.controller;

import com.spring.labs.lab6.dto.MovieScreeningDTO;
import com.spring.labs.lab6.entity.MovieScreening;
import com.spring.labs.lab6.exception.response.ExceptionResponse;
import com.spring.labs.lab6.service.MovieScreeningService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/screenings")
@Tag(name = "Screenings API", description = "Operations for managing movie screenings")
public class MovieScreeningController {

    private final MovieScreeningService movieScreeningService;

    @Autowired
    public MovieScreeningController(MovieScreeningService movieScreeningService) {
        this.movieScreeningService = movieScreeningService;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get screening by ID", description = "Returns a single movie screening by it's ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Screening found",
                    content = @Content(schema = @Schema(implementation = MovieScreeningDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid path variable",
                    content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
            @ApiResponse(responseCode = "404", description = "Screening not found",
                    content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = ExceptionResponse.class)))
    })
    public ResponseEntity<MovieScreeningDTO> getScreening(
            @Parameter(description = "ID of the screening to retrieve", example = "11",
                    schema = @Schema(type = "long"))
            @PathVariable(name = "id") long id
    ) {
        return ResponseEntity.ok(MovieScreeningDTO.fromEntity(movieScreeningService.getScreeningById(id)));
    }

    @GetMapping
    @Operation(summary = "Get the list of all screenings", description = "Returns all movie screenings of the cinema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Screenings returned",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = MovieScreeningDTO.class)))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = ExceptionResponse.class)))
    })
    public ResponseEntity<List<MovieScreeningDTO>> getAllScreenings() {
        return ResponseEntity.ok(movieScreeningService.getAllScreenings().stream()
                .map(MovieScreeningDTO::fromEntity)
                .toList()
        );
    }

    @PostMapping
    @Operation(summary = "Create a screening", description = "Creates a single movie screening")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Screening created",
                    content = @Content(schema = @Schema(implementation = MovieScreeningDTO.class))),
            @ApiResponse(responseCode = "409", description = "Screening that is created already exists",
                    content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = ExceptionResponse.class)))
    })
    public ResponseEntity<MovieScreeningDTO> createScreening(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Screening information for creation",
                    required = true,
                    content = @Content(schema = @Schema(implementation = MovieScreeningDTO.class),
                            examples = {
                                    @ExampleObject(
                                            name = "Sample movie screening",
                                            summary = "Example of screening creation",
                                            value = "{\"date\":\"2025-11-16T10:00\",\"movieName\":\"The Amazing Spider-Man\",\"cinemaHall\":3}"
                                    )
                            })
            )
            @RequestBody MovieScreeningDTO screening
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(MovieScreeningDTO.fromEntity(
                        movieScreeningService.createScreening(
                        new MovieScreening(null, screening.date(), screening.movieName(), screening.cinemaHall())
                )));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a screening", description = "Update movie screening's details by it's ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Screening updated",
                    content = @Content(schema = @Schema(implementation = MovieScreeningDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid path variable",
                    content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
            @ApiResponse(responseCode = "409", description = "Screening that is updated already exists",
                    content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = ExceptionResponse.class)))
    })
    public ResponseEntity<MovieScreeningDTO> updateScreening(
            @Parameter(description = "ID of the screening to update", example = "11",
                    schema = @Schema(type = "long"))
            @PathVariable(name = "id") long id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Screening information for update",
                    required = true,
                    content = @Content(schema = @Schema(implementation = MovieScreeningDTO.class),
                            examples = {
                                    @ExampleObject(
                                            name = "Sample movie screening",
                                            summary = "Example of screening update",
                                            value = "{\"date\":\"2025-11-22T18:00\",\"movieName\":\"The Amazing Spider-Man\",\"cinemaHall\":1}"
                                    )
                            })
            )
            @RequestBody MovieScreeningDTO screening
    ) {
        return ResponseEntity.ok(
                MovieScreeningDTO.fromEntity(
                        movieScreeningService.updateScreening(
                                new MovieScreening(null, screening.date(), screening.movieName(), screening.cinemaHall()),
                                id
                        )
                )
        );
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a screening", description = "Delete a single movie screening by it's ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Screening deleted",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid path variable",
                    content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = ExceptionResponse.class)))
    })
    public ResponseEntity<Void> deleteScreening(
            @Parameter(description = "ID of the screening to delete", example = "11",
                    schema = @Schema(type = "long"))
            @PathVariable(name = "id") long id
    ) {
        movieScreeningService.deleteScreeningById(id);
        return ResponseEntity.noContent().build();
    }
}
