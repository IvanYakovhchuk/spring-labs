package com.spring.labs.lab6.service.impl;

import com.spring.labs.lab6.entity.Seat;
import com.spring.labs.lab6.exception.NoSeatFoundException;
import com.spring.labs.lab6.exception.SeatAlreadyExistsException;
import com.spring.labs.lab6.repository.SeatRepository;
import com.spring.labs.lab6.service.SeatService;
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
        return seatRepository.findSeatByIdUsingQueryAnnotation(id)
                .orElseThrow(() -> new NoSeatFoundException("No seat found with such id: " + id + "."));
    }

    @Override
    public List<Seat> getAllSeats() {
        return seatRepository.findAll();
    }

    @Transactional
    @Override
    public Seat createSeat(Seat seat) {
        seatRepository.findByCinemaHallAndRowAndNumber(seat.getCinemaHall(), seat.getRow(), seat.getNumber())
                .ifPresent(s -> {
                    throw new SeatAlreadyExistsException("Such seat already exists!");
                });
        seat.setId(null);
        return seatRepository.save(seat);
    }

    @Transactional
    @Override
    public Seat updateSeat(Seat newSeat, long id) {
        Optional<Seat> existingSeat = seatRepository
                .findByCinemaHallAndRowAndNumber(newSeat.getCinemaHall(), newSeat.getRow(), newSeat.getNumber());

        if (existingSeat.isPresent() && existingSeat.get().getId() != id) {
            throw new SeatAlreadyExistsException("Cannot update seat. Such seat already exists!");
        }

        Seat oldSeat = seatRepository.findSeatByIdUsingNamedQueryAnnotation(id)
                .orElseThrow(() -> new NoSeatFoundException("No seat found with such id: " + id + "."));
        oldSeat.setCinemaHall(newSeat.getCinemaHall());
        oldSeat.setRow(newSeat.getRow());
        oldSeat.setNumber(newSeat.getNumber());
        oldSeat.setVip(newSeat.isVip());
        seatRepository.save(oldSeat);
        return oldSeat;
    }

    @Transactional
    @Override
    public void deleteSeatById(long id) {
        seatRepository.deleteById(id);
    }
}
