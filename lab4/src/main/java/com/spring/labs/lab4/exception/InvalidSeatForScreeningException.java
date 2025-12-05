package com.spring.labs.lab4.exception;

public class InvalidSeatForScreeningException extends RuntimeException {
    public InvalidSeatForScreeningException(String message) {
        super(message);
    }
}
