package com.spring.labs.lab5.exception;

public class InvalidSeatForScreening extends RuntimeException {
    public InvalidSeatForScreening() {
        super("Seat is not in the same hall as the screening");
    }
}
