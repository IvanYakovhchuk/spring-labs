package com.spring.labs.lab5.entity;

import java.time.LocalDateTime;

public class MovieScreening {
    private long id;
    private LocalDateTime screeningDate;
    private String movieName;
    private int cinemaHall;

    public MovieScreening() {
    }

    public MovieScreening(long id, LocalDateTime date, String movieName, int cinemaHall) {
        this.id = id;
        this.screeningDate = date;
        this.movieName = movieName;
        this.cinemaHall = cinemaHall;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getScreeningDate() {
        return screeningDate;
    }

    public void setScreeningDate(LocalDateTime screeningDate) {
        this.screeningDate = screeningDate;
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


