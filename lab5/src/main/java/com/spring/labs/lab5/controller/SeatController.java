package com.spring.labs.lab5.controller;

import com.spring.labs.lab5.dto.SeatDTO;
import com.spring.labs.lab5.entity.Seat;
import com.spring.labs.lab5.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/seats")
public class SeatController {

    private final SeatService seatService;

    @Autowired
    public SeatController(SeatService seatService) {
        this.seatService = seatService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<SeatDTO> getSeat(@PathVariable(value = "id") long id) {
        Seat seat = seatService.getSeatById(id);

        return ResponseEntity.ok(
                SeatDTO.fromEntity(seat)
        );
    }

    @GetMapping
    public ResponseEntity<List<SeatDTO>> getAllSeats() {
        return ResponseEntity.ok(
                seatService.getAllSeats().stream()
                        .map(SeatDTO::fromEntity)
                        .toList()
        );
    }

    @PostMapping
    public ResponseEntity<SeatDTO> createSeat(@RequestBody SeatDTO seat) {
        Seat newSeat = seatService.createSeat(new Seat(
                0, seat.getCinemaHall(), seat.getRowNumber(), seat.getSeatNumber(), seat.getIsVip())
        );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(SeatDTO.fromEntity(newSeat));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SeatDTO> updateSeat(@PathVariable(name = "id") long id,
                                              @RequestBody SeatDTO seat) {
        Seat updatedSeat = seatService.updateSeat(
                new Seat(0, seat.getCinemaHall(), seat.getRowNumber(), seat.getSeatNumber(), seat.getIsVip()),
                id
        );

        return ResponseEntity.ok(SeatDTO.fromEntity(updatedSeat));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSeat(@PathVariable(name = "id") long id) {
        seatService.deleteSeatById(id);
        return ResponseEntity.noContent().build();
    }
}
