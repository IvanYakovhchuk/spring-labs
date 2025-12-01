package com.spring.labs.lab3.exception;

public class InvalidSeatForScreeningException extends RuntimeException {
    public InvalidSeatForScreeningException(String message) {
        super(message);
    }
}
