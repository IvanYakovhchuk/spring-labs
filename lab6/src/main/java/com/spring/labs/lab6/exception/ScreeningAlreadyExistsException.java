package com.spring.labs.lab6.exception;

public class ScreeningAlreadyExistsException extends RuntimeException {
    public ScreeningAlreadyExistsException(String message) {
        super(message);
    }
}
