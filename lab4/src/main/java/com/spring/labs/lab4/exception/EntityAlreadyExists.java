package com.spring.labs.lab4.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class EntityAlreadyExists extends ResponseStatusException {
    public EntityAlreadyExists(String entityName) {
        super(HttpStatus.CONFLICT, entityName + " already exists");
    }

    public EntityAlreadyExists(String entityName, String sameCriteria) {
        super(HttpStatus.CONFLICT, entityName + " with the same " + sameCriteria + " already exists");
    }
}
