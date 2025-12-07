package com.spring.labs.lab6.exception;

public class SeatAlreadyExistsException extends RuntimeException {
    public SeatAlreadyExistsException(String message) {
        super(message);
    }
}
