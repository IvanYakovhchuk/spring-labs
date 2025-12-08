package com.spring.labs.lab5.exception;

public class NoScreeningFound extends NoEntityFound {
    public NoScreeningFound() {
        super("Screening");
    }

    public NoScreeningFound(Long id) {
        super("Screening", id);
    }
}
