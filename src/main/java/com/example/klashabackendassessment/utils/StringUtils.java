package com.example.klashabackendassessment.utils;

public class StringUtils {
  public static String capitalizeFirstCharacter(String input) {
    if (input == null || input.isEmpty()) {
      return input;
    }
    return Character.toUpperCase(input.charAt(0)) + input.substring(1).toLowerCase();
  }
}
