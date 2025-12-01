package com.spring.labs.lab3.exception;

public class NoScreeningFound extends RuntimeException {
    public NoScreeningFound(String message) {
        super(message);
    }
}
