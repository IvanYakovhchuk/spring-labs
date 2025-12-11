package com.spring.labs.lab5.exception;

public class NoTicketFoundException extends NotFoundException {
    public NoTicketFoundException(String message) {
        super(message);
    }
}
