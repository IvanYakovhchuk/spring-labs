package com.spring.labs.lab6.repository;

import com.spring.labs.lab6.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {
    @Query(value = "SELECT s FROM Seat s WHERE s.id = :id")
    Optional<Seat> findSeatByIdUsingQueryAnnotation(@Param("id") long id);
    Optional<Seat> findSeatByIdUsingNamedQueryAnnotation(@Param("id") long id);
    Optional<Seat> findByCinemaHallAndRowAndNumber(int cinemaHall, int row, int number);
}
