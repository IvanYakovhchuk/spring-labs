package com.spring.labs.lab5.exception;

public class TicketAlreadyExists extends EntityAlreadyExists {
    public TicketAlreadyExists() {
        super("Ticket");
    }

    public TicketAlreadyExists(String sameCriteria) {
        super("Ticket", sameCriteria);
    }
}
