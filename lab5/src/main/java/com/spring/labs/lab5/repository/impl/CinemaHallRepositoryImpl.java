package com.spring.labs.lab5.repository.impl;

import com.spring.labs.lab5.entity.CinemaHall;
import com.spring.labs.lab5.repository.CinemaHallRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Repository
public class CinemaHallRepositoryImpl implements CinemaHallRepository {
    private final JdbcTemplate jdbcTemplate;

    public CinemaHallRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final RowMapper<CinemaHall> CINEMA_HALL_MAPPER = (rs, rowNum) -> {
        CinemaHall hall = new CinemaHall();
        hall.setId(rs.getLong("id"));
        hall.setName(rs.getString("name"));
        return hall;
    };

    @Override
    public long create(CinemaHall hall) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement("INSERT INTO cinema_halls (name) VALUES (?)", new String[]{"id"});
            ps.setString(1, hall.getName());
            return ps;
        }, keyHolder);
        return keyHolder.getKey().longValue();
    }

    @Override
    public Optional<CinemaHall> findById(long id) {
        return jdbcTemplate.query("SELECT * FROM cinema_halls WHERE id = ?", CINEMA_HALL_MAPPER, id).stream().findFirst();
    }

    @Override
    public void update(long id, CinemaHall hall) {
        jdbcTemplate.update("UPDATE cinema_halls SET name = ? WHERE id = ?", hall.getName(), hall.getId());
    }

    @Override
    public void delete(long id) {
        jdbcTemplate.update("DELETE FROM cinema_halls WHERE id = ?", id);
    }


    @Override
    public List<CinemaHall> findAll() {
        return jdbcTemplate.query("SELECT * FROM cinema_halls", CINEMA_HALL_MAPPER);
    }
}
