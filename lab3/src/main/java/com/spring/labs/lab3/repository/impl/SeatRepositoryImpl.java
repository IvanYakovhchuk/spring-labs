package com.spring.labs.lab3.repository.impl;

import com.spring.labs.lab3.entity.Seat;
import com.spring.labs.lab3.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class SeatRepositoryImpl implements SeatRepository {

    private final List<Seat> seats;

    @Autowired
    public SeatRepositoryImpl(List<Seat> seats) {
        this.seats = seats;
    }

    @Override
    public Optional<Seat> findById(long id) {
        return seats.stream()
                .filter(s -> s.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Seat> findAll() {
        return new ArrayList<>(seats);
    }

    @Override
    public Seat save(Seat seat) {
        seats.add(seat);
        return seat;
    }

    @Override
    public Seat update(long id, Seat newSeat) {
        Seat oldSeat = findById(id).orElse(null);
        if (oldSeat != null) {
            oldSeat.setCinemaHall(newSeat.getCinemaHall());
            oldSeat.setRow(newSeat.getRow());
            oldSeat.setNumber(newSeat.getNumber());
            oldSeat.setVip(newSeat.isVip());
        }
        return oldSeat;
    }


    @Override
    public boolean delete(long id) {
        return seats.removeIf(s -> s.getId().equals(id));
    }
}
