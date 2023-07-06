package com.example.klashabackendassessment.app.validators;

import static com.example.klashabackendassessment.app.enums.Currency.NGN;
import static com.example.klashabackendassessment.app.enums.Currency.UGX;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TargetCurrencyValidator implements ConstraintValidator<ValidTargetCurrency, Enum> {
  public TargetCurrencyValidator() {}

  @Override
  public boolean isValid(Enum value, ConstraintValidatorContext context) {
    if (value == null) {
      return false;
    }
    return value.toString().equalsIgnoreCase(NGN.toString())
        || value.toString().equalsIgnoreCase(UGX.toString());
  }
}
