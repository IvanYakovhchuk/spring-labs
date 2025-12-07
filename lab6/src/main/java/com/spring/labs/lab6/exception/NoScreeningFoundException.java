package com.spring.labs.lab6.exception;

public class NoScreeningFoundException extends RuntimeException {
    public NoScreeningFoundException(String message) {
        super(message);
    }
}
