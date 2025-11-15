package com.spring.labs.lab2.controller;

import com.spring.labs.lab2.entity.MovieScreening;
import com.spring.labs.lab2.entity.Seat;
import com.spring.labs.lab2.entity.Ticket;
import com.spring.labs.lab2.service.MovieScreeningService;
import com.spring.labs.lab2.service.SeatService;
import com.spring.labs.lab2.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@Controller
public class GuestController {

    private final SeatService seatService;
    private final MovieScreeningService movieScreeningService;
    private final TicketService ticketService;

    @Autowired
    public GuestController(SeatService seatService, MovieScreeningService movieScreeningService, TicketService ticketService) {
        this.seatService = seatService;
        this.movieScreeningService = movieScreeningService;
        this.ticketService = ticketService;
    }

    @GetMapping("/")
    public String guest(Model theModel) {
        theModel.addAttribute("screenings", movieScreeningService.getAllScreenings());
        return "index";
    }

    @GetMapping("/{screeningId}/seats")
    public String bookSeats(@PathVariable("screeningId") long screeningId, Model theModel) {
        MovieScreening screening = movieScreeningService.getScreeningById(screeningId);
        List<Seat> allSeats = seatService.getAllSeatsOfCinemaHall(screening.getCinemaHall());

        Map<Integer, List<Seat>> seatsByRow = allSeats.stream()
                .sorted(Comparator.comparingInt(Seat::getNumber))
                .collect(Collectors.groupingBy(Seat::getRow, LinkedHashMap::new, Collectors.toList()));

        Map<Long, Boolean> bookedMap = screening.getBookedSeatsIds().stream()
                .collect(Collectors.toMap(id -> id, id -> true));

        theModel.addAttribute("seatsByRow", seatsByRow);
        theModel.addAttribute("bookedMap", bookedMap);
        theModel.addAttribute("screening", screening);

        return "seats-booking-page";
    }

    @PostMapping("/book-ticket")
    public String bookTicket(
            @RequestParam("seatId") Long seatId,
            @RequestParam("screeningId") Long screeningId,
            @RequestParam("customerName") String customerName) {

        Ticket _ = ticketService.addTicket(new Ticket(null, screeningId, seatId, customerName, new Random().nextInt(100, 1000)));

        return "redirect:/";
    }
}
