package com.spring.labs.lab6.exception;

public class NoSeatFoundException extends NotFoundException {
    public NoSeatFoundException(String message) {
        super(message);
    }
}
