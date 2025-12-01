package com.spring.labs.lab3.exception;

public class NoSeatFound extends RuntimeException {
    public NoSeatFound(String message) {
        super(message);
    }
}
