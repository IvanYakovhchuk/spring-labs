package com.spring.labs.lab4.exception;

public class TicketAlreadyExistsException extends RuntimeException {
    public TicketAlreadyExistsException(String message) {
        super(message);
    }
}
