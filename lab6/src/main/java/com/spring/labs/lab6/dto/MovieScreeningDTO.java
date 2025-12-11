package com.spring.labs.lab6.dto;

import com.spring.labs.lab6.entity.MovieScreening;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "Data Transfer Object representing a Ticket")
public record MovieScreeningDTO(
        @Schema(
                description = "Date and time of screening the movie in the cinema",
                example = "2026-01-03T14:00:00",
                type = "string"
        )
        LocalDateTime date,
        @Schema(description = "Name of the movie", example = "The Green Mile") String movieName,
        @Schema(description = "Cinema hall where the screening of the movie will take place", example = "2") int cinemaHall
) {
    public static MovieScreeningDTO fromEntity(MovieScreening screening) {
        return new MovieScreeningDTO(
                screening.getDate(), screening.getMovieName(), screening.getCinemaHall()
        );
    }
}
