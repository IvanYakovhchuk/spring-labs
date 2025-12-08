package com.spring.labs.lab5.dto;

import com.spring.labs.lab5.entity.MovieScreening;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public class MovieScreeningDTO {
    @Schema(description = "Movie screening id")
    private Long id;

    @Schema(description = "Movie screening start time")
    private LocalDateTime date;

    @Schema(description = "Movie screening title")
    private String movieName;

    @Schema(description = "Movie screening cinema hall number")
    private int cinemaHall;

    public MovieScreeningDTO(Long id, LocalDateTime date, String movieName, int cinemaHall) {
        this.id = id;
        this.date = date;
        this.movieName = movieName;
        this.cinemaHall = cinemaHall;
    }

    public static MovieScreeningDTO fromEntity(MovieScreening screening) {
        return new MovieScreeningDTO(
            screening.getId(),
            screening.getScreeningDate(),
            screening.getMovieName(),
            screening.getCinemaHall()
        );
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public int getCinemaHall() {
        return cinemaHall;
    }

    public void setCinemaHall(int cinemaHall) {
        this.cinemaHall = cinemaHall;
    }
}
