package com.spring.labs.lab6.exception;

public class InvalidSeatForScreeningException extends RuntimeException {
    public InvalidSeatForScreeningException(String message) {
        super(message);
    }
}
