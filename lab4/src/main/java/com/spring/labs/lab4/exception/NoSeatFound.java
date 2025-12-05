package com.spring.labs.lab4.exception;

public class NoSeatFound extends RuntimeException {
    public NoSeatFound(String message) {
        super(message);
    }
}
