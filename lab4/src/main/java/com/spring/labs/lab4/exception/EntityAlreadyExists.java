package com.spring.labs.lab4.exception;

public class EntityAlreadyExists extends RuntimeException {
    public EntityAlreadyExists(String entityName) {
        super(entityName + " already exists");
    }

    public EntityAlreadyExists(String entityName, String sameCriteria) {
        super(entityName + " with the same " + sameCriteria + " already exists");
    }
}
