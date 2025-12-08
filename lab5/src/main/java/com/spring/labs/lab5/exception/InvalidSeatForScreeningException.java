package com.spring.labs.lab5.exception;

public class InvalidSeatForScreeningException extends RuntimeException {
    public InvalidSeatForScreeningException(String message) {
        super(message);
    }
}
