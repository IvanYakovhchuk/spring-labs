package com.spring.labs.lab5.service.impl;

import com.spring.labs.lab5.entity.Seat;
import com.spring.labs.lab5.exception.NoSeatFoundException;
import com.spring.labs.lab5.exception.SeatAlreadyExistsException;
import com.spring.labs.lab5.repository.SeatRepository;
import com.spring.labs.lab5.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class SeatServiceImpl implements SeatService {

    private final SeatRepository seatRepository;

    @Autowired
    public SeatServiceImpl(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    @Override
    public Seat getSeatById(long id) {
        return seatRepository.findById(id)
                .orElseThrow(() -> new NoSeatFoundException("No seat found with such id: " + id + "."));
    }

    @Override
    public List<Seat> getAllSeats() {
        return seatRepository.findAll();
    }

    @Transactional
    @Override
    public Seat createSeat(Seat seat) {
        seatRepository.findByCinemaHallAndRowAndNumber(seat.getCinemaHall(), seat.getRowNumber(), seat.getSeatNumber())
                .ifPresent(s -> {
                    throw new SeatAlreadyExistsException("Such seat already exists!");
                });
        return seatRepository.create(seat);
    }

    @Transactional
    @Override
    public Seat updateSeat(Seat newSeat, long id) {
        Optional<Seat> existingSeat = seatRepository
                .findByCinemaHallAndRowAndNumber(newSeat.getCinemaHall(), newSeat.getRowNumber(), newSeat.getSeatNumber());

        if (existingSeat.isPresent() && existingSeat.get().getId() != id) {
            throw new SeatAlreadyExistsException("Cannot update seat. Such seat already exists!");
        }

        Seat oldSeat = seatRepository.findById(id)
                .orElseThrow(() -> new NoSeatFoundException("No seat found with such id: " + id + "."));

        return seatRepository.update(id, newSeat);
    }

    @Transactional
    @Override
    public void deleteSeatById(long id) {
        seatRepository.delete(id);
    }
}
