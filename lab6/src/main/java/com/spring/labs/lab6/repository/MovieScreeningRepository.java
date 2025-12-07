package com.spring.labs.lab6.repository;

import com.spring.labs.lab6.entity.MovieScreening;
import com.spring.labs.lab6.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MovieScreeningRepository extends JpaRepository<MovieScreening, Long> {
    @Query(value = "SELECT ms FROM MovieScreening ms WHERE ms.id = :id")
    Optional<MovieScreening> findMovieScreeningByIdUsingQueryAnnotation(@Param("id") long id);

    Optional<MovieScreening> findMovieScreeningByIdUsingNamedQueryAnnotation(@Param("id") long id);
}
