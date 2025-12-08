package com.spring.labs.lab4.service;

import com.spring.labs.lab4.dto.FilterMovieScreeningDTO;
import com.spring.labs.lab4.dto.Page;
import com.spring.labs.lab4.dto.PaginationDTO;
import com.spring.labs.lab4.dto.SortOrderDTO;
import com.spring.labs.lab4.entity.MovieScreening;
import jakarta.validation.Valid;

import java.util.List;

public interface MovieScreeningService {
    MovieScreening getScreeningById(long id);
    Page<MovieScreening> getAllScreenings(FilterMovieScreeningDTO filterDTO, PaginationDTO paginationDTO, SortOrderDTO sortOrderDTO);
    MovieScreening addScreening(MovieScreening screening);
    MovieScreening updateScreeningById(long id, MovieScreening newScreening);
    boolean removeScreeningById(long id);
}
