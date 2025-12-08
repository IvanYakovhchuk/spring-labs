package com.spring.labs.lab5.repository.impl;

import com.spring.labs.lab5.entity.Ticket;
import com.spring.labs.lab5.repository.TicketRepository;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TicketRepositoryImpl implements TicketRepository {
    private final JdbcClient jdbcClient;

    public TicketRepositoryImpl(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public long create(Ticket ticket) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcClient.sql("""
                INSERT INTO tickets (screening_id, seat_id, customer_name, price)
                VALUES (:screeningId, :seatId, :customerName, :price)
                RETURNING *
            """)
            .paramSource(ticket)
            .update(keyHolder);
        return keyHolder.getKey().longValue();
    }

    @Override
    public Optional<Ticket> findById(long id) {
        return jdbcClient.sql("SELECT * FROM tickets WHERE id = ?")
            .param(1, id)
            .query(Ticket.class)
            .optional();
    }

    @Override
    public void update(long id, Ticket ticket) {
        ticket.setId(id);
        jdbcClient.sql("""
                UPDATE tickets
                SET screening_id = :screeningId, seat_id = :seatId, customer_name = :customerName, price = :price
                WHERE id = :id
            """)
            .paramSource(ticket)
            .update();
    }

    @Override
    public void delete(long id) {
        jdbcClient.sql("DELETE FROM tickets WHERE id = ?")
            .param(1, id)
            .update();
    }

    @Override
    public List<Ticket> findAll() {
        return jdbcClient.sql("SELECT * FROM tickets")
            .query(Ticket.class)
            .list();
    }
}
