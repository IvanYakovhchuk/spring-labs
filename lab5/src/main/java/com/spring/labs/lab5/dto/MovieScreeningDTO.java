package com.spring.labs.lab5.dto;

import com.spring.labs.lab5.entity.MovieScreening;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "Data Transfer Object representing a movie screening")
public record MovieScreeningDTO (
    @Schema(description = "Movie screening id")
    long id,
    @Schema(description = "Movie screening start time")
    LocalDateTime date,
    @Schema(description = "Movie screening title")
    String movieName,
    @Schema(description = "Movie screening cinema hall number")
    int cinemaHall
) {
    public static MovieScreeningDTO fromEntity(MovieScreening screening) {
        return new MovieScreeningDTO(
            screening.getId(),
            screening.getScreeningDate(),
            screening.getMovieName(),
            screening.getCinemaHall()
        );
    }
}
