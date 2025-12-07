package com.spring.labs.lab6.exception;

public class NoTicketFoundException extends RuntimeException {
    public NoTicketFoundException(String message) {
        super(message);
    }
}
