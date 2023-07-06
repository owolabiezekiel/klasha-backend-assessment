package com.example.klashabackendassessment.app.validators;

import java.lang.annotation.*;
import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {TargetCurrencyValidator.class})
public @interface ValidTargetCurrency {
  String message() default "conversion not supported for target currency";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
