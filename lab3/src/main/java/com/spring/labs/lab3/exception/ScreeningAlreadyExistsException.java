package com.spring.labs.lab3.exception;

public class ScreeningAlreadyExistsException extends RuntimeException {
    public ScreeningAlreadyExistsException(String message) {
        super(message);
    }
}
