package com.spring.labs.lab2.exception;

public class ScreeningAlreadyExistsException extends RuntimeException {
    public ScreeningAlreadyExistsException(String message) {
        super(message);
    }
}
