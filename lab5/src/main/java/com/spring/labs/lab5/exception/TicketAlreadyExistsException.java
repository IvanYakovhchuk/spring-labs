package com.spring.labs.lab5.exception;

public class TicketAlreadyExistsException extends AlreadyExistsException {
    public TicketAlreadyExistsException(String message) {
        super(message);
    }
}
