package com.spring.labs.lab6.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Converter(autoApply = true)
public class LocalDateTimeAttributeConverter implements AttributeConverter<LocalDateTime, String> {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @Override
    public String convertToDatabaseColumn(LocalDateTime date) {
        return date != null ? date.format(FORMATTER) : null;
    }

    @Override
    public LocalDateTime convertToEntityAttribute(String value) {
        return value != null ? LocalDateTime.parse(value, FORMATTER) : null;
    }
}
