package com.example.klashabackendassessment.app.enums;

import static com.example.klashabackendassessment.utils.StringUtils.isNullOrBlank;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.stream.Stream;

public enum Currency {
  NGN,
  UGX,
  EUR,
  USD,
  JPY,
  GBP,
  UNKNOWN;

  @JsonCreator
  public static Currency create(String value) {
    if (isNullOrBlank(value)) {
      return null;
    }

    return Stream.of(Currency.values())
        .filter(e -> e.toString().equalsIgnoreCase(value))
        .findFirst()
        .orElse(UNKNOWN);
  }

  @JsonValue
  public String getName() {
    return name().toLowerCase();
  }
}
