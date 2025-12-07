package com.spring.labs.lab6.exception.response;

import java.time.LocalDateTime;

public record ExceptionResponse(int status, String message, LocalDateTime time) {
}
