package com.spring.labs.lab4.configuration;

import com.spring.labs.lab4.dto.ValidationErrorDTO;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalControllerAdvice {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorDTO> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, List<String>> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            var fieldName = ((FieldError) error).getField();
            errors.putIfAbsent(fieldName, new LinkedList<>());
            errors.get(fieldName).add(error.getDefaultMessage());
        });

        var dto = new ValidationErrorDTO(errors);
        return ResponseEntity.badRequest().body(dto);
    }

    @ExceptionHandler(HandlerMethodValidationException.class)
    public ResponseEntity<ValidationErrorDTO> handleException(HandlerMethodValidationException ex) {
        Map<String, List<String>> errors = new HashMap<>();

        ex.getParameterValidationResults().forEach((param) -> {
            var name = param.getMethodParameter().getParameterName();
            var errorList = param.getResolvableErrors()
                .stream()
                .map(MessageSourceResolvable::getDefaultMessage)
                .toList();
            errors.put(name, errorList);
        });

        var dto = new ValidationErrorDTO(errors);
        return ResponseEntity.badRequest().body(dto);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ErrorResponse handleResponseStatusException(ResponseStatusException ex) {
        return ex;
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ErrorResponse handleNoResourceFoundException(NoResourceFoundException ex) {
        return new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ErrorResponse handleServerException(Exception ex) {
        ex.printStackTrace(System.err);
        return new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error");
    }
}
