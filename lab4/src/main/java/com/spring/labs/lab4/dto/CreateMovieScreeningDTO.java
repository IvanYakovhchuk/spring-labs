package com.spring.labs.lab4.dto;

import com.spring.labs.lab4.entity.MovieScreening;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

public class CreateMovieScreeningDTO {
    @NotBlank
    private String movieName;

    @NotNull
    @Min(1)
    private int cinemaHall;

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
