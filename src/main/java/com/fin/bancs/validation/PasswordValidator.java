package com.fin.bancs.validation;

import com.fin.bancs.validation.annotations.ValidPassword;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


public class PasswordValidator implements ConstraintValidator<ValidPassword, String> {

    @Override
    public void initialize(ValidPassword constraintAnnotation) {
        // Initialization logic, if needed
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        // Validation logic for password
        if (password == null) {
            return false; // Null values are invalid
        }

        // Check for at least 8 characters, at least one uppercase letter, one lowercase letter, one digit, and one special character
        return password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$");
    }
}
