package com.spring.labs.lab6.controller;

import com.spring.labs.lab6.dto.SeatDTO;
import com.spring.labs.lab6.entity.Seat;
import com.spring.labs.lab6.exception.response.ExceptionResponse;
import com.spring.labs.lab6.service.SeatService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/seats")
@Tag(name = "Seats API", description = "Operations for managing seats")
public class SeatController {

    private final SeatService seatService;

    @Autowired
    public SeatController(SeatService seatService) {
        this.seatService = seatService;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get seat by ID", description = "Returns a single seat by it's ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Seat found",
                    content = @Content(schema = @Schema(implementation = SeatDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid path variable",
                    content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
            @ApiResponse(responseCode = "404", description = "Seat not found",
                    content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = ExceptionResponse.class)))
    })
    public ResponseEntity<SeatDTO> getSeat(
            @Parameter(description = "ID of the seat to retrieve", example = "7",
                    schema = @Schema(type = "long"))
            @PathVariable(value = "id") long id
    ) {
        Seat seat = seatService.getSeatById(id);

        return ResponseEntity.ok(
                SeatDTO.fromEntity(seat)
        );
    }

    @GetMapping
    @Operation(summary = "Get the list of all seats", description = "Returns all seats in the cinema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Seats returned",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = SeatDTO.class)))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = ExceptionResponse.class)))
    })
    public ResponseEntity<List<SeatDTO>> getAllSeats() {
        return ResponseEntity.ok(
                seatService.getAllSeats().stream()
                        .map(SeatDTO::fromEntity)
                        .toList()
        );
    }

    @PostMapping
    @Operation(summary = "Create a seat", description = "Creates a single seat")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Seat created",
                    content = @Content(schema = @Schema(implementation = SeatDTO.class))),
            @ApiResponse(responseCode = "409", description = "Seat that is created already exists",
                    content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = ExceptionResponse.class)))
    })
    public ResponseEntity<SeatDTO> createSeat(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Seat information for creation",
                    required = true,
                    content = @Content(schema = @Schema(implementation = SeatDTO.class),
                            examples = {
                                    @ExampleObject(
                                            name = "Sample seat",
                                            summary = "Example of seat creation",
                                            value = "{\"cinemaHall\":1,\"row\":2,\"number\":12,\"vip\":false}"
                                    )
                            })
            )
            @RequestBody SeatDTO seat
    ) {
        Seat newSeat = seatService.createSeat(new Seat(
                null, seat.cinemaHall(), seat.row(), seat.number(), seat.vip())
        );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(SeatDTO.fromEntity(newSeat));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a seat", description = "Update seat's details by it's ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Seat updated",
                    content = @Content(schema = @Schema(implementation = SeatDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid path variable",
                    content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
            @ApiResponse(responseCode = "409", description = "Seat that is updated already exists",
                    content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = ExceptionResponse.class)))
    })
    public ResponseEntity<SeatDTO> updateSeat(
            @Parameter(description = "ID of the seat to update", example = "3",
                    schema = @Schema(type = "long"))
            @PathVariable(name = "id") long id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Seat information for update",
                    required = true,
                    content = @Content(schema = @Schema(implementation = SeatDTO.class),
                            examples = {
                                    @ExampleObject(
                                            name = "Sample seat",
                                            summary = "Example of seat update",
                                            value = "{\"cinemaHall\":1,\"row\":3,\"number\":12,\"vip\":true}"
                                    )
                            })
            )
            @RequestBody SeatDTO seat
    ) {
        Seat updatedSeat = seatService.updateSeat(
                new Seat(null, seat.cinemaHall(), seat.row(), seat.number(), seat.vip()),
                id
        );

        return ResponseEntity.ok(SeatDTO.fromEntity(updatedSeat));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a seat", description = "Delete a single seat by it's ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Seat deleted",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid path variable",
                    content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = ExceptionResponse.class)))
    })
    public ResponseEntity<Void> deleteSeat(
            @Parameter(description = "ID of the seat to delete", example = "7",
                    schema = @Schema(type = "long"))
            @PathVariable(name = "id") long id
    ) {
        seatService.deleteSeatById(id);
        return ResponseEntity.noContent().build();
    }
}
