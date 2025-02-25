package com.fin.bancs.validation;

import com.fin.bancs.validation.annotations.ValidCard;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CardValidator implements ConstraintValidator<ValidCard, String> {

    @Override
    public boolean isValid(String cardNumber, ConstraintValidatorContext context) {
        if (cardNumber == null || cardNumber.isEmpty()) {
            return false;
        }

        cardNumber = cardNumber.replaceAll("\\s", ""); // Remove spaces
        return cardNumber.matches("^4[0-9]{12}(?:[0-9]{3})?$") || // Visa
                cardNumber.matches("^5[1-5][0-9]{14}$") ||          // MasterCard
                cardNumber.matches("^3[47][0-9]{13}$");             // Amex
    }
}
