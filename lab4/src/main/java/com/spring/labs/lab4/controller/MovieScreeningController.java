package com.spring.labs.lab4.controller;

import com.spring.labs.lab4.service.MovieScreeningService;
import com.spring.labs.lab4.service.SeatService;
import com.spring.labs.lab4.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "movieScreenings")
public class MovieScreeningController {

    private final SeatService seatService;
    private final MovieScreeningService movieScreeningService;
    private final TicketService ticketService;

    @Autowired
    public MovieScreeningController(SeatService seatService, MovieScreeningService movieScreeningService, TicketService ticketService) {
        this.seatService = seatService;
        this.movieScreeningService = movieScreeningService;
        this.ticketService = ticketService;
    }
}
