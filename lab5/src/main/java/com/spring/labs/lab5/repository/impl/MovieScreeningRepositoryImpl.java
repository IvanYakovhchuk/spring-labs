package com.spring.labs.lab5.repository.impl;

import com.spring.labs.lab5.entity.MovieScreening;
import com.spring.labs.lab5.repository.MovieScreeningRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class MovieScreeningRepositoryImpl implements MovieScreeningRepository {
    private final JdbcTemplate jdbcTemplate;

    public MovieScreeningRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final RowMapper<MovieScreening> MOVIE_SCREENING_MAPPER = (rs, rowNum) -> {
        MovieScreening screening = new MovieScreening();
        screening.setId(rs.getLong("id"));
        screening.setScreeningDate(rs.getObject("screening_date", LocalDateTime.class));
        screening.setMovieName(rs.getString("movie_name"));
        screening.setCinemaHallId(rs.getLong("cinema_hall"));
        return screening;
    };

    @Override
    public long create(MovieScreening screening) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO movie_screenings (screening_date, movie_name, cinema_hall) VALUES (?, ?, ?)",
                new String[]{"id"}
            );
            ps.setObject(1, screening.getScreeningDate());
            ps.setString(2, screening.getMovieName());
            ps.setLong(3, screening.getCinemaHallId());
            return ps;
        }, keyHolder);
        return keyHolder.getKey().longValue();
    }

    @Override
    public Optional<MovieScreening> findById(long id) {
        return jdbcTemplate.query("SELECT * FROM movie_screenings WHERE id = ?", MOVIE_SCREENING_MAPPER, id).stream().findFirst();
    }

    @Override
    public void update(long id, MovieScreening screening) {
        jdbcTemplate.update(
            "UPDATE movie_screenings SET screening_date = ?, movie_name = ?, cinema_hall = ? WHERE id = ?",
            screening.getScreeningDate(), screening.getMovieName(), screening.getCinemaHallId(), id
        );
    }

    @Override
    public void delete(long id) {
        jdbcTemplate.update("DELETE FROM movie_screenings WHERE id = ?", id);
    }

    @Override
    public List<MovieScreening> findAll() {
        return jdbcTemplate.query("SELECT * FROM movie_screenings", MOVIE_SCREENING_MAPPER);
    }
}
