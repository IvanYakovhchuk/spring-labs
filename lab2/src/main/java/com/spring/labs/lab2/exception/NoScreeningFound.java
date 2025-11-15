package com.spring.labs.lab2.exception;

public class NoScreeningFound extends RuntimeException {
    public NoScreeningFound(String message) {
        super(message);
    }
}
