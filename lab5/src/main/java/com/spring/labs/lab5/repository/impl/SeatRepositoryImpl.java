package com.spring.labs.lab5.repository.impl;

import com.spring.labs.lab5.entity.Seat;
import com.spring.labs.lab5.repository.SeatRepository;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class SeatRepositoryImpl implements SeatRepository {

    private final JdbcClient jdbcClient;

    public SeatRepositoryImpl(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public Seat create(Seat seat) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcClient.sql("""
                INSERT INTO seats (cinema_hall, row_number, seat_number, is_vip)
                VALUES (:cinemaHall, :rowNumber, :seatNumber, :isVip)
                RETURNING id
            """)
            .paramSource(seat)
            .update(keyHolder);
        seat.setId(keyHolder.getKey().longValue());
        return seat;
    }

    @Override
    public Optional<Seat> findById(long id) {
        return jdbcClient.sql("SELECT * FROM seats WHERE id = ?")
            .param(1, id)
            .query(Seat.class)
            .optional();
    }

    @Override
    public Optional<Seat> findByCinemaHallAndRowAndNumber(int cinemaHall, int rowNumber, int seatNumber) {
        return jdbcClient.sql("SELECT * FROM seats WHERE cinema_hall = ? AND row_number = ? AND seat_number = ?")
                .param(1, cinemaHall)
                .param(2,  rowNumber)
                .param(3,  seatNumber)
                .query(Seat.class)
                .optional();
    }

    @Override
    public Seat update(long id, Seat seat) {
        seat.setId(id);
        jdbcClient.sql("""
                UPDATE seats
                SET cinema_hall = :cinemaHallId, row_number = :rowNumber, seat_number = :seatNumber, is_vip = :vip
                WHERE id = :id
            """)
            .paramSource(seat)
            .update();
        return seat;
    }

    @Override
    public void delete(long id) {
        jdbcClient.sql("DELETE FROM seats WHERE id = ?")
            .param(1, id)
            .update();
    }

    @Override
    public List<Seat> findAll() {
        return jdbcClient.sql("SELECT * FROM seats")
            .query(Seat.class)
            .list();
    }
}
