package com.spring.labs.lab4.exception;

public class NoEntityFound extends RuntimeException {

    public NoEntityFound(String entityName, Long id) {
        super(entityName + " with id " + id + " not found");
    }

    public NoEntityFound(String entityName) {
        super(entityName + " not found");
    }
}
