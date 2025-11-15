package com.spring.labs.lab2.exception;

public class InvalidSeatForScreeningException extends RuntimeException {
    public InvalidSeatForScreeningException(String message) {
        super(message);
    }
}
