package com.spring.labs.lab4.dto;

import com.spring.labs.lab4.entity.MovieScreening;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

@Schema(description = "Information for movie screening creation")
public class CreateMovieScreeningDTO {
    @Schema(description = "Movie screening title")
    @NotBlank
    private String movieName;

    @Schema(description = "Movie screening cinema hall number")
    @NotNull
    @Min(1)
    private int cinemaHall;

    @Schema(description = "Movie screening start time (up to minutes)")
    @NotNull
    @FutureOrPresent
    private LocalDateTime date;

    public CreateMovieScreeningDTO() {}

    public CreateMovieScreeningDTO(String movieName, int cinemaHall, LocalDateTime date) {
        this.movieName = movieName;
        this.cinemaHall = cinemaHall;
        this.date = date;
    }

    public MovieScreening toEntity() {
        return new MovieScreening(null, date, movieName, cinemaHall);
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public int getCinemaHall() {
        return cinemaHall;
    }

    public void setCinemaHall(int cinemaHall) {
        this.cinemaHall = cinemaHall;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
