package com.spring.labs.lab4.dto;

import com.spring.labs.lab4.entity.MovieScreening;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.List;

public class MovieScreeningDTO {
    @Schema(description = "Movie screening id")
    private Long id;

    @Schema(description = "Movie screening start time")
    private LocalDateTime date;

    @Schema(description = "Movie screening title")
    private String movieName;

    @Schema(description = "Movie screening cinema hall number")
    private int cinemaHall;

    @Schema(description = "List of ids for seats booked for the screening")
    private List<Long> bookedSeatsIds;

    public MovieScreeningDTO() {
    }

    public MovieScreeningDTO(Long id, LocalDateTime date, String movieName, int cinemaHall, List<Long> bookedSeatsIds) {
        this.id = id;
        this.date = date;
        this.movieName = movieName;
        this.cinemaHall = cinemaHall;
        this.bookedSeatsIds = bookedSeatsIds;
    }

    public static MovieScreeningDTO fromEntity(MovieScreening screening) {
        return new MovieScreeningDTO(
            screening.getId(),
            screening.getDate(),
            screening.getMovieName(),
            screening.getCinemaHall(),
            screening.getBookedSeatsIds().stream().toList()
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

    public List<Long> getBookedSeatsIds() {
        return bookedSeatsIds;
    }

    public void setBookedSeatsIds(List<Long> bookedSeatsIds) {
        this.bookedSeatsIds = bookedSeatsIds;
    }
}
