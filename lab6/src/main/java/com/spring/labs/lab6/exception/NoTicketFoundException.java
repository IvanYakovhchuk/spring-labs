package com.spring.labs.lab6.exception;

public class NoTicketFoundException extends NotFoundException {
    public NoTicketFoundException(String message) {
        super(message);
    }
}
