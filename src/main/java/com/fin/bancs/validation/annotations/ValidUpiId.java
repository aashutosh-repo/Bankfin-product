package com.fin.bancs.validation.annotations;

import com.fin.bancs.validation.UpiIdValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Constraint(validatedBy = UpiIdValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidUpiId {
    String message() default "Invalid UPI ID";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
