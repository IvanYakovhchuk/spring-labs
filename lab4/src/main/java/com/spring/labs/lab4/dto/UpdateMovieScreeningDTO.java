package com.spring.labs.lab4.dto;

import com.spring.labs.lab4.annotation.NullOrNotBlank;
import com.spring.labs.lab4.entity.MovieScreening;
import jakarta.validation.constraints.Min;

import java.time.LocalDateTime;

public class UpdateMovieScreeningDTO {
    @NullOrNotBlank
    private String movieName;

    @Min(1)
    private Integer cinemaHall;

    private LocalDateTime date;

    public UpdateMovieScreeningDTO() {}

    public UpdateMovieScreeningDTO(String movieName, Integer cinemaHall, LocalDateTime date) {
        this.movieName = movieName;
        this.cinemaHall = cinemaHall;
        this.date = date;
    }

    public void updateEntity(MovieScreening movieScreening) {
        if (movieName != null) {
            movieScreening.setMovieName(movieName);
        }
        if (cinemaHall != null) {
            movieScreening.setCinemaHall(cinemaHall);
        }
        if (date != null) {
            movieScreening.setDate(date);
        }
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public Integer getCinemaHall() {
        return cinemaHall;
    }

    public void setCinemaHall(Integer cinemaHall) {
        this.cinemaHall = cinemaHall;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
