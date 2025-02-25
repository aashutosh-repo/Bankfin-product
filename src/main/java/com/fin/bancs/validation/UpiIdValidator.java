package com.fin.bancs.validation;

import com.fin.bancs.validation.annotations.ValidCard;
import com.fin.bancs.validation.annotations.ValidUpiId;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UpiIdValidator implements ConstraintValidator<ValidUpiId, String> {
    @Override
    public boolean isValid(String upiId, ConstraintValidatorContext context) {

        if(upiId == null || upiId.isEmpty()){
            return false;
        }
        return upiId.matches("^[a-zA-Z0-9.\\-_]{2,256}@[a-zA-Z]{2,64}$");
    }
}
