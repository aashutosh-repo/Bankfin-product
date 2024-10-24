package com.fin.bancs.validation.annotations;

import com.fin.bancs.validation.PasswordValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = PasswordValidator.class) //validator class
@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE, ElementType.PARAMETER }) // Where this annotation can be applied
@Retention(RetentionPolicy.RUNTIME) // Retain at runtime for validation
public @interface ValidPassword {
    String message() default "Weak password"; // Default error message

    Class<?>[] groups() default {}; // Used for grouping constraints

    Class<? extends Payload>[] payload() default {}; // Additional data
}