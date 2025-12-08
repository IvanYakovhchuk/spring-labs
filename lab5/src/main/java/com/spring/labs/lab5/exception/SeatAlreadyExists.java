package com.spring.labs.lab5.exception;

public class SeatAlreadyExists extends EntityAlreadyExists {
    public SeatAlreadyExists() {
        super("Seat");
    }

    public SeatAlreadyExists(String sameCriteria) {
        super("Seat", sameCriteria);
    }
}
