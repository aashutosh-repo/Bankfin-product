package com.fin.bancs.validation;


import com.fin.bancs.validation.annotations.ValidUsername;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UsernameValidator implements ConstraintValidator<ValidUsername, String> {

    @Override
    public void initialize(ValidUsername constraintAnnotation) {
        // Initialization logic, if needed
    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        // Validation logic for username
        if (username == null) {
            return false; // Null values are invalid
        }
        // Username must be 3 to 15 characters and can contain letters, digits, and underscores
        return username.matches("^[a-zA-Z0-9_]{3,15}$");
    }
}

