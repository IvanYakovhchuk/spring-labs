package com.spring.labs.lab4.dto;

import java.time.LocalDateTime;
import java.util.List;

public class MovieScreeningDTO {
    private Long id;
    private LocalDateTime date;
    private String movieName;
    private int cinemaHall;
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
