package com.spring.labs.lab4.service;

import com.spring.labs.lab4.dto.*;
import com.spring.labs.lab4.entity.MovieScreening;

public interface MovieScreeningService {
    MovieScreening getScreeningById(long id);
    Page<MovieScreening> getAllScreenings(FilterMovieScreeningDTO filterDTO, PaginationDTO paginationDTO, SortMovieScreeningDTO sortDTO);
    MovieScreening addScreening(CreateMovieScreeningDTO screening);
    MovieScreening updateScreeningById(long id, UpdateMovieScreeningDTO newScreening);
    boolean removeScreeningById(long id);
}
