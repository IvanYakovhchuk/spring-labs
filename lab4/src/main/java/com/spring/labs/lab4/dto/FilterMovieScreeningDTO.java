package com.spring.labs.lab4.dto;

import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public class FilterMovieScreeningDTO {
    @Parameter(description = "Date for which to return screenings")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime date;

    @Parameter(description = "Movie name to include")
    private String movieName;

    @Parameter(description = "Cinema hall for which to return screenings")
    private Integer cinemaHall;

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
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
}
