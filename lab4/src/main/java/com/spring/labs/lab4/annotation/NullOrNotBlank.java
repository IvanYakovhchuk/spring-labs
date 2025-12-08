package com.spring.labs.lab4.annotation;

import com.spring.labs.lab4.validator.NullOrNotBlankValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = NullOrNotBlankValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface NullOrNotBlank {
    String message() default "Must not be blank";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}