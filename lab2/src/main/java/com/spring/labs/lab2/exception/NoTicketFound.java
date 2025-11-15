package com.spring.labs.lab2.exception;

public class NoTicketFound extends RuntimeException {
    public NoTicketFound(String message) {
        super(message);
    }
}
