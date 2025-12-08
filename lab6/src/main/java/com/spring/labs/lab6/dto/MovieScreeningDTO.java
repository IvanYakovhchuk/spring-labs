package com.spring.labs.lab6.dto;

import com.spring.labs.lab6.entity.MovieScreening;

import java.time.LocalDateTime;

public record MovieScreeningDTO(LocalDateTime date, String movieName, int cinemaHall) {
    public static MovieScreeningDTO fromEntity(MovieScreening screening) {
        return new MovieScreeningDTO(
                screening.getDate(), screening.getMovieName(), screening.getCinemaHall()
        );
    }
}
