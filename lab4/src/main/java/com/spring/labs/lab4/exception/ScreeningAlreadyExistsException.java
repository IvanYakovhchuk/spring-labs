package com.spring.labs.lab4.exception;

public class ScreeningAlreadyExistsException extends RuntimeException {
    public ScreeningAlreadyExistsException(String message) {
        super(message);
    }
}
