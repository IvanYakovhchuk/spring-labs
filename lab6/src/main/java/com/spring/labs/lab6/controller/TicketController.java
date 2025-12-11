package com.spring.labs.lab6.controller;

import com.spring.labs.lab6.dto.TicketCreateDTO;
import com.spring.labs.lab6.dto.TicketDTO;
import com.spring.labs.lab6.entity.MovieScreening;
import com.spring.labs.lab6.entity.Seat;
import com.spring.labs.lab6.entity.Ticket;
import com.spring.labs.lab6.exception.response.ExceptionResponse;
import com.spring.labs.lab6.service.MovieScreeningService;
import com.spring.labs.lab6.service.SeatService;
import com.spring.labs.lab6.service.TicketService;
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
@RequestMapping("/api/v1/tickets")
@Tag(name = "Tickets API", description = "Operations for managing tickets")
public class TicketController {

    private final SeatService seatService;
    private final MovieScreeningService movieScreeningService;
    private final TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService,
                            SeatService seatService,
                            MovieScreeningService movieScreeningService) {
        this.ticketService = ticketService;
        this.seatService = seatService;
        this.movieScreeningService = movieScreeningService;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get ticket by ID", description = "Returns a single ticket by it's ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ticket found",
                     content = @Content(schema = @Schema(implementation = TicketDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid path variable",
                    content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
            @ApiResponse(responseCode = "404", description = "Ticket not found",
                     content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = ExceptionResponse.class)))
    })
    public ResponseEntity<TicketDTO> getTicket(
            @Parameter(description = "ID of the ticket to retrieve", example = "123",
                       schema = @Schema(type = "long"))
            @PathVariable(name = "id") long id
    ) {
        return ResponseEntity.ok(TicketDTO.fromEntity(ticketService.getTicketById(id)));
    }

    @GetMapping
    @Operation(summary = "Get the list of all tickets", description = "Returns all tickets that were booked in the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tickets returned",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = TicketDTO.class)))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = ExceptionResponse.class)))
    })
    public ResponseEntity<List<TicketDTO>> getAllTickets() {
        return ResponseEntity.ok(ticketService.getAllTickets()
                .stream()
                .map(TicketDTO::fromEntity)
                .toList());
    }

    @PostMapping
    @Operation(summary = "Create a ticket", description = "Creates a single ticket")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Ticket created",
                         content = @Content(schema = @Schema(implementation = TicketDTO.class))),
            @ApiResponse(responseCode = "409", description = "Ticket for these seat and screening has already been booked",
                    content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
            @ApiResponse(responseCode = "422", description = "Screening will not be shown in the cinema hall mentioned in the ticket",
                    content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = ExceptionResponse.class)))
    })
    public ResponseEntity<TicketDTO> createTicket(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Ticket information for creation",
                    required = true,
                    content = @Content(schema = @Schema(implementation = TicketCreateDTO.class),
                                       examples = {
                                           @ExampleObject(
                                                name = "Sample ticket",
                                                summary = "Example of ticket creation",
                                                value = "{\"seatId\":1,\"screeningId\":2,\"customerName\":\"John Doe\",\"price\":150.0}"
                                           )
                                       })
            )
            @RequestBody TicketCreateDTO ticketCreateDTO,
            @Parameter(description = "Transaction failure", example = "true/false",
                    schema = @Schema(type = "boolean"))
            @RequestParam(name = "fail", defaultValue = "false", required = false) boolean forceFail
    ) {
        Seat seat = seatService.getSeatById(ticketCreateDTO.seatId());
        MovieScreening screening = movieScreeningService.getScreeningById(ticketCreateDTO.screeningId());

        Ticket newTicket = ticketService.createTicket(
                new Ticket(null, seat, screening, ticketCreateDTO.customerName(), ticketCreateDTO.price()),
                forceFail
        );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(TicketDTO.fromEntity(newTicket));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a ticket", description = "Update ticket's details by it's ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ticket updated",
                    content = @Content(schema = @Schema(implementation = TicketDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid path variable",
                    content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
            @ApiResponse(responseCode = "409", description = "Ticket that is updated for these seat and screening has already been booked",
                    content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
            @ApiResponse(responseCode = "422", description = "Screening will not be shown in the cinema hall mentioned in the updated ticket",
                    content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = ExceptionResponse.class)))
    })
    public ResponseEntity<TicketDTO> updateTicket(
            @Parameter(description = "ID of the ticket to update", example = "123",
                    schema = @Schema(type = "long"))
            @PathVariable(name = "id") long id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Ticket information for update",
                    required = true,
                    content = @Content(schema = @Schema(implementation = TicketCreateDTO.class),
                            examples = {
                                    @ExampleObject(
                                            name = "Sample ticket",
                                            summary = "Example of ticket update",
                                            value = "{\"seatId\":7,\"screeningId\":2,\"customerName\":\"John Doe\",\"price\":200.0}"
                                    )
                            })
            )
            @RequestBody TicketCreateDTO ticketCreateDTO
    ) {
        Seat seat = seatService.getSeatById(ticketCreateDTO.seatId());
        MovieScreening screening = movieScreeningService.getScreeningById(ticketCreateDTO.screeningId());

        Ticket newTicket = ticketService.updateTicket(
                new Ticket(null, seat, screening, ticketCreateDTO.customerName(), ticketCreateDTO.price()),
                id
        );

        return ResponseEntity.ok(TicketDTO.fromEntity(newTicket));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a ticket", description = "Delete a single ticket by it's ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Ticket deleted",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid path variable",
                    content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = ExceptionResponse.class)))
    })
    public ResponseEntity<Void> deleteTicket(
            @Parameter(description = "ID of the ticket to delete", example = "123",
                    schema = @Schema(type = "long"))
            @PathVariable(name = "id") long id
    ) {
        ticketService.deleteTicketById(id);
        return ResponseEntity.noContent().build();
    }
}
