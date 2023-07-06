package com.example.klashabackendassessment.utils;

import static java.util.stream.Collectors.toList;

import java.util.List;
import lombok.experimental.UtilityClass;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindingResult;

@UtilityClass
public class ErrorFormatter {
  public static List<String> format(BindingResult bindingResult) {
    List<String> errors =
        bindingResult.getFieldErrors().stream()
            .map(DefaultMessageSourceResolvable::getDefaultMessage)
            .distinct()
            .collect(toList());

    bindingResult.getGlobalErrors().stream()
        .map(DefaultMessageSourceResolvable::getDefaultMessage)
        .distinct()
        .forEach(errors::add);

    return errors;
  }
}
