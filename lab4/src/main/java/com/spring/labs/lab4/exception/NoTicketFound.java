package com.spring.labs.lab4.exception;

public class NoTicketFound extends RuntimeException {
    public NoTicketFound(String message) {
        super(message);
    }
}
