package com.spring.labs.lab4.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidSeatForScreening extends ResponseStatusException {
    public InvalidSeatForScreening() {
        super(HttpStatus.CONFLICT, "Seat is not in the same hall as the screening");
    }
}
