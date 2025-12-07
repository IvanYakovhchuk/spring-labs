package com.spring.labs.lab4.dto;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class ValidationErrorDTO {
    private final int status;
    private final String message;
    private final Map<String, List<String>> errors;
    private final LocalDateTime timestamp;

    public ValidationErrorDTO(Map<String, List<String>> errors) {
        this.status = HttpStatus.BAD_REQUEST.value();
        this.message = "Validation error";
        this.errors = errors;
        this.timestamp = LocalDateTime.now();
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public Map<String, List<String>> getErrors() {
        return errors;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
