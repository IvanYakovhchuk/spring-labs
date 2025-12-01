package com.spring.labs.lab3.exception;

public class NoTicketFound extends RuntimeException {
    public NoTicketFound(String message) {
        super(message);
    }
}
