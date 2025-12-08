package com.spring.labs.lab4.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class NoEntityFound extends ResponseStatusException {

    public NoEntityFound(String entityName, Long id) {
        super(HttpStatus.BAD_REQUEST, entityName + " with id " + id + " not found");
    }

    public NoEntityFound(String entityName) {
        super(HttpStatus.BAD_REQUEST, entityName + " not found");
    }
}
