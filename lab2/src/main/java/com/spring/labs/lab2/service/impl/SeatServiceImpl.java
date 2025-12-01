package com.spring.labs.lab2.service.impl;

import com.spring.labs.lab2.entity.Seat;
import com.spring.labs.lab2.exception.NoSeatFound;
import com.spring.labs.lab2.exception.SeatAlreadyExistsException;
import com.spring.labs.lab2.repository.SeatRepository;
import com.spring.labs.lab2.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SeatServiceImpl implements SeatService {

    private SeatRepository seatRepository;

    @Autowired
    public void setSeatRepository(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    public Seat getSeatById(long id) {
        return seatRepository.findById(id)
                .orElseThrow(() -> new NoSeatFound("Seat not found with id: " + id));
    }

    public List<Seat> getAllSeats() {
        return seatRepository.findAll();
    }

    public Seat addSeat(Seat seat) {
        boolean duplicate = seatRepository.findAll().stream()
                .anyMatch(s -> s.getId().equals(seat.getId()) || isSeatInfoEqual(s, seat));

        if (duplicate) {
            throw new SeatAlreadyExistsException("Seat with same id or same hall/row/number already exists.");
        }

        seatRepository.save(seat);
        return seat;
    }

    public Seat updateSeatById(long id, Seat newSeat) {
        Seat oldSeat = seatRepository.findById(id)
                .orElseThrow(() -> new NoSeatFound("Seat not found with id: " + id));

        boolean duplicate = seatRepository.findAll().stream()
                .anyMatch(s -> !s.getId().equals(id) && isSeatInfoEqual(s, newSeat));

        if (duplicate) {
            throw new SeatAlreadyExistsException("Another seat with the same hall/row/number already exists.");
        }

        oldSeat.setCinemaHall(newSeat.getCinemaHall());
        oldSeat.setRow(newSeat.getRow());
        oldSeat.setNumber(newSeat.getNumber());
        oldSeat.setVip(newSeat.isVip());

        return oldSeat;
    }

    public boolean removeSeatById(long id) {
        return seatRepository.delete(id);
    }

    public List<Seat> getAllSeatsOfCinemaHall(int cinemaHall) {
        return seatRepository.findAll().stream()
                .filter(s -> s.getCinemaHall() == cinemaHall)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private boolean isSeatInfoEqual(Seat seat1, Seat seat2) {
        return seat1.getCinemaHall() == seat2.getCinemaHall() &&
                seat1.getRow() == seat2.getRow() &&
                seat1.getNumber() == seat2.getNumber();
    }
}
