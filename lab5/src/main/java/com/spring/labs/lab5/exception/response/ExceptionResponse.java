package com.spring.labs.lab5.exception.response;

import java.time.LocalDateTime;

public record ExceptionResponse(int status, String message, LocalDateTime time) {
}
