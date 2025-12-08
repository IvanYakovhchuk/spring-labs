package com.spring.labs.lab6.repository;

import com.spring.labs.lab6.entity.MovieScreening;
import com.spring.labs.lab6.entity.Seat;
import com.spring.labs.lab6.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    @Query(value = "SELECT t FROM Ticket t WHERE t.id = :id")
    Optional<Ticket> findTicketByIdUsingQueryAnnotation(@Param("id") long id);
    Optional<Ticket> findTicketByIdUsingNamedQueryAnnotation(@Param("id") long id);
    Optional<Ticket> findBySeatAndScreening(Seat seat, MovieScreening screening);
}
