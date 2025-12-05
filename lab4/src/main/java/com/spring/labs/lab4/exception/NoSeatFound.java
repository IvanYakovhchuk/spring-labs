package com.spring.labs.lab4.exception;

public class NoSeatFound extends NoEntityFound {
    public NoSeatFound() {
        super("Seat");
    }

    public NoSeatFound(Long id) {
        super("Seat", id);
    }
}
