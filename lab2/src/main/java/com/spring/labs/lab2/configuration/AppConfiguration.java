package com.spring.labs.lab2.configuration;

import com.spring.labs.lab2.entity.MovieScreening;
import com.spring.labs.lab2.entity.Seat;
import com.spring.labs.lab2.entity.Ticket;
import com.spring.labs.lab2.repository.MovieScreeningRepository;
import com.spring.labs.lab2.repository.TicketRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Configuration
public class AppConfiguration {

    @Bean
    @Scope("singleton")
    public List<Ticket> tickets() {
        try (Stream<String> data = Files.lines(Path.of("src/main/resources/static/tickets.txt"))) {
            return data.skip(1)
                    .map(line -> line.split(","))
                    .map(fields -> new Ticket(
                            Long.parseLong(fields[0]),
                            Long.parseLong(fields[1]),
                            Long.parseLong(fields[2]),
                            fields[3],
                            Double.parseDouble(fields[4])
                    ))
                    .collect(Collectors.toCollection(ArrayList::new));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Bean
    @Scope("singleton")
    public List<MovieScreening> screenings() {
        try (Stream<String> data = Files.lines(Path.of("src/main/resources/static/screenings.txt"))) {
            return data.skip(1)
                    .map(line -> line.split(","))
                    .map(fields -> new MovieScreening(
                            Long.parseLong(fields[0]),
                            LocalDateTime.parse(fields[1]),
                            fields[2],
                            Integer.parseInt(fields[3])
                    ))
                    .collect(Collectors.toCollection(ArrayList::new));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Bean
    @Scope("singleton")
    public List<Seat> seats() {
        try (Stream<String> data = Files.lines(Path.of("src/main/resources/static/seats.txt"))) {
            return data.skip(1)
                    .map(line -> line.split(","))
                    .map(fields -> new Seat(
                            Long.parseLong(fields[0]),
                            Integer.parseInt(fields[1]),
                            Integer.parseInt(fields[2]),
                            Integer.parseInt(fields[3]),
                            Boolean.parseBoolean(fields[4])
                    ))
                    .collect(Collectors.toCollection(ArrayList::new));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Bean
    @Scope("prototype")
    public CommandLineRunner connectTicketsToScreenings(
            MovieScreeningRepository screeningRepo,
            TicketRepository ticketRepo
    ) {
        return args -> {
            for (Ticket ticket : ticketRepo.findAll()) {
                MovieScreening s = screeningRepo.findById(ticket.getScreeningId())
                        .orElseThrow(() -> new RuntimeException("No screening"));

                s.getBookedSeatsIds().add(ticket.getSeatId());
            }
        };
    }

}
