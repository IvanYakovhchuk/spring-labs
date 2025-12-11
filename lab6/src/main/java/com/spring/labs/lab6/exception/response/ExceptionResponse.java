package com.spring.labs.lab6.exception.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "Data Transfer Object representing a response for an error")
public record ExceptionResponse(
        @Schema(description = "HTTP status code for an error", examples = {"400", "404", "409", "422", "500"}) int status,
        @Schema(description = "Error message", example = "Unexpected error: something went wrong") String message,
        @Schema(
                description = "Time when an error occurred",
                example = "2025-12-15T18:25:06",
                type = "string"
        )
        LocalDateTime time
) { }
