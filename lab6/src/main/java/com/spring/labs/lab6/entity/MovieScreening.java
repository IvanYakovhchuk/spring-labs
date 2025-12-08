package com.spring.labs.lab6.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "movie_screening")
@NamedQuery(
        name = "MovieScreening.findMovieScreeningByIdUsingNamedQueryAnnotation",
        query = "SELECT ms FROM MovieScreening ms WHERE ms.id = :id"
)
public class MovieScreening {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "date")
    private LocalDateTime date;
    @Column(name = "movie_name")
    private String movieName;
    @Column(name = "cinema_hall")
    private int cinemaHall;
    @OneToMany(mappedBy = "screening", fetch = FetchType.LAZY)
    private final Set<Ticket> tickets = new HashSet<>();

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
        return tickets.stream()
                .map(Ticket::getSeat)
                .map(Seat::getId)
                .collect(Collectors.toSet());
    }

    @Override
    public String toString() {
        return "MovieScreening{" +
                "id=" + id +
                ", date=" + date +
                ", movieName='" + movieName + '\'' +
                ", cinemaHall=" + cinemaHall +
                ", bookedSeatsIds=" + getBookedSeatsIds() +
                '}';
    }
}


