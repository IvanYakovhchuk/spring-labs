package com.spring.labs.lab4.configuration;

import com.spring.labs.lab4.entity.MovieScreening;
import com.spring.labs.lab4.entity.Seat;
import com.spring.labs.lab4.entity.Ticket;
import com.spring.labs.lab4.exception.NoScreeningFound;
import com.spring.labs.lab4.repository.MovieScreeningRepository;
import com.spring.labs.lab4.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.Resource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Configuration
public class AppConfiguration {

    @Value("classpath:data/tickets.csv")
    private Resource ticketsFile;

    @Value("classpath:data/screenings.csv")
    private Resource screeningsFile;

    @Value("classpath:data/seats.csv")
    private Resource seatsFile;

    private Stream<String> getResourceLines(Resource resource) throws IOException {
        var inputStream = resource.getInputStream();
        var streamReader = new InputStreamReader(inputStream);
        var bufferedReader = new BufferedReader(streamReader);
        return bufferedReader.lines();
    }

    @Bean
    @Scope("singleton")
    @Profile("seeding")
    public List<Ticket> tickets() {
        try (Stream<String> data = getResourceLines(ticketsFile)) {
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
    @Profile("seeding")
    public List<MovieScreening> screenings() {
        try (Stream<String> data = getResourceLines(screeningsFile)) {
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
    @Profile("seeding")
    public List<Seat> seats() {
        try (Stream<String> data = getResourceLines(seatsFile)) {
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
                        .orElseThrow(NoScreeningFound::new);

                s.getBookedSeatsIds().add(ticket.getSeatId());
            }
        };
    }

}
