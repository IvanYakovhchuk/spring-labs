package com.spring.labs.lab4.repository;

import com.spring.labs.lab4.dto.*;
import com.spring.labs.lab4.entity.MovieScreening;

import java.util.List;
import java.util.Optional;

public interface MovieScreeningRepository {
    Optional<MovieScreening> findById(long id);
    Page<MovieScreening> findAll(FilterMovieScreeningDTO filterDTO, PaginationDTO paginationDTO, SortOrderDTO sortOrderDTO);
    List<MovieScreening> findAll();
    MovieScreening save(MovieScreening screening);
    MovieScreening update(long id, MovieScreening newScreening);
    boolean delete(long id);
}
