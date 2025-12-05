package com.spring.labs.lab4.exception;

public class SeatAlreadyExistsException extends RuntimeException {
    public SeatAlreadyExistsException(String message) {
        super(message);
    }
}
