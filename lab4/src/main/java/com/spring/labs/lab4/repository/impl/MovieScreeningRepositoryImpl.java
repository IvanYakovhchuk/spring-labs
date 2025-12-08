package com.spring.labs.lab4.repository.impl;

import com.spring.labs.lab4.dto.*;
import com.spring.labs.lab4.entity.MovieScreening;
import com.spring.labs.lab4.repository.MovieScreeningRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.function.Predicate;

@Repository
public class MovieScreeningRepositoryImpl implements MovieScreeningRepository {

    private final List<MovieScreening> screenings;

    @Autowired
    public MovieScreeningRepositoryImpl(List<MovieScreening> screenings) {
        this.screenings = screenings;
    }

    @Override
    public Optional<MovieScreening> findById(long id) {
        return screenings.stream()
                .filter(s -> s.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<MovieScreening> findAll() {
        return new ArrayList<>(screenings);
    }

    @Override
    public Page<MovieScreening> findAll(
            FilterMovieScreeningDTO filterDTO,
            PaginationDTO paginationDTO,
            SortOrderDTO sortOrderDTO) {
        int skip = (paginationDTO.getPage() - 1) * paginationDTO.getPageSize();
        int take = paginationDTO.getPageSize();
        var filter = getFilter(filterDTO);
        var comparator = getComparator(sortOrderDTO);
        var items = screenings.stream()
                .filter(filter)
                .sorted(comparator)
                .skip(skip)
                .limit(take)
                .toList();
        int total = (int) screenings.stream()
                .filter(getFilter(filterDTO))
                .count();
        int pageSize = items.size();
        int totalPages = Math.ceilDiv(total, paginationDTO.getPageSize());
        return new Page<>(paginationDTO, pageSize, totalPages, total, items);
    }

    private Predicate<MovieScreening> getFilter(FilterMovieScreeningDTO filterDTO) {
        List<Predicate<MovieScreening>> predicates = new LinkedList<>();

        predicates.add(x -> true);

        if (filterDTO.getDate() != null) {
            predicates.add(screening -> screening.getDate()
                    .truncatedTo(ChronoUnit.DAYS).isEqual(filterDTO.getDate().truncatedTo(ChronoUnit.DAYS)));
        }

        if (filterDTO.getMovieName() != null) {
            predicates.add(screening ->
                    screening.getMovieName().toLowerCase().contains(filterDTO.getMovieName().toLowerCase()));
        }

        if (filterDTO.getCinemaHall() != null) {
            predicates.add(screening -> filterDTO.getCinemaHall().equals(screening.getCinemaHall()));
        }

        return predicates.stream().reduce(Predicate::and).orElse(null);
    }

    private Comparator<MovieScreening> getComparator(SortOrderDTO orderDTO) {
        var order = orderDTO.getOrderBy();
        Comparator<MovieScreening> comparator = null;
        if (order.equals("id")) {
            comparator = Comparator.comparing(MovieScreening::getId);
        } else if (order.equals("date")) {
            comparator = Comparator.comparing(MovieScreening::getDate);
        } else if (order.equals("movieName")) {
            comparator = Comparator.comparing(MovieScreening::getMovieName);
        } else if (order.equals("cinemaHall")) {
            comparator = Comparator.comparing(MovieScreening::getCinemaHall);
        }
        if (comparator == null) comparator = Comparator.comparing(MovieScreening::getId);
        if (orderDTO.getOrder() == SortOrder.DESC) {
            comparator = comparator.reversed();
        }
        return comparator;
    }

    @Override
    public MovieScreening save(MovieScreening screening) {
        if (screening.getId() == null) {
            var lastId = screenings.stream()
                    .map(MovieScreening::getId)
                    .max(Comparator.naturalOrder())
                    .orElse(0L);
            screening.setId(lastId + 1);
        }
        screenings.add(screening);
        return screening;
    }

    @Override
    public MovieScreening updateById(long id, UpdateMovieScreeningDTO newScreening) {
        MovieScreening oldScreening = findById(id).orElse(null);
        if (oldScreening != null) {
            newScreening.updateEntity(oldScreening);
        }
        return oldScreening;
    }

    @Override
    public boolean delete(long id) {
        return screenings.removeIf(s -> s.getId().equals(id));
    }
}
