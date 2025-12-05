package com.spring.labs.lab4.exception;

public class ScreeningAlreadyExists extends EntityAlreadyExists {
    public ScreeningAlreadyExists() {
        super("Screening");
    }

    public ScreeningAlreadyExists(String sameCriteria) {
        super("Screening", sameCriteria);
    }
}
