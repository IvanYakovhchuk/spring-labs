package com.spring.labs.lab6.exception;

public class NoScreeningFoundException extends NotFoundException {
    public NoScreeningFoundException(String message) {
        super(message);
    }
}
