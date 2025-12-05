package com.spring.labs.lab4.exception;

public class SeatAlreadyExists extends EntityAlreadyExists {
    public SeatAlreadyExists() {
        super("Seat");
    }

    public SeatAlreadyExists(String sameCriteria) {
        super("Seat", sameCriteria);
    }
}
