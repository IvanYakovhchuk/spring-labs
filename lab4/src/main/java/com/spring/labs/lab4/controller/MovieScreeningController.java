package com.spring.labs.lab4.controller;

import com.spring.labs.lab4.service.MovieScreeningService;
import com.spring.labs.lab4.service.SeatService;
import com.spring.labs.lab4.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "movieScreenings")
public class MovieScreeningController {

    private final MovieScreeningService movieScreeningService;

    @Autowired
    public MovieScreeningController(MovieScreeningService movieScreeningService) {
        this.movieScreeningService = movieScreeningService;
    }

}
