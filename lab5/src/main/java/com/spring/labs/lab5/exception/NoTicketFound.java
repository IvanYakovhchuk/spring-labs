package com.spring.labs.lab5.exception;

public class NoTicketFound extends NoEntityFound {
    public NoTicketFound() {
        super("Ticket");
    }

    public NoTicketFound(Long id) {
        super("Ticket", id);
    }
}
