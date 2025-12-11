package com.spring.labs.lab5.exception;

public class SeatAlreadyExistsException extends AlreadyExistsException {
    public SeatAlreadyExistsException(String message) {
        super(message);
    }
}
