package com.spring.labs.lab2.exception;

public class NoSeatFound extends RuntimeException {
    public NoSeatFound(String message) {
        super(message);
    }
}
