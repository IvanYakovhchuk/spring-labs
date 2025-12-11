package com.spring.labs.lab5.exception;

public class NoScreeningFoundException extends NotFoundException {
    public NoScreeningFoundException(String message) {
        super(message);
    }
}
