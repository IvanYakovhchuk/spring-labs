package com.spring.labs.lab4.exception;

public class NoScreeningFound extends RuntimeException {
    public NoScreeningFound(String message) {
        super(message);
    }
}
