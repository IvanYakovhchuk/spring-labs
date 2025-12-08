package com.spring.labs.lab6.exception;

public class TicketAlreadyExistsException extends AlreadyExistsException {
    public TicketAlreadyExistsException(String message) {
        super(message);
    }
}
