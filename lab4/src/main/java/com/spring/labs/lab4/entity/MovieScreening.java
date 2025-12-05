package com.spring.labs.lab4.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class MovieScreening {

    private Long id;
    private LocalDateTime date;
    private String movieName;
    private int cinemaHall;

    private final Set<Long> bookedSeatsIds = new HashSet<>();

    public MovieScreening() {
    }

    public MovieScreening(Long id, LocalDateTime date, String movieName, int cinemaHall) {
        this.id = id;
        this.date = date;
        this.movieName = movieName;
        this.cinemaHall = cinemaHall;
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

    public Set<Long> getBookedSeatsIds() {
        return bookedSeatsIds;
    }

    @Override
    public String toString() {
        return "MovieScreening{" +
                "id=" + id +
                ", date=" + date +
                ", movieName='" + movieName + '\'' +
                ", cinemaHall=" + cinemaHall +
                ", bookedSeatsIds=" + bookedSeatsIds +
                '}';
    }
}


