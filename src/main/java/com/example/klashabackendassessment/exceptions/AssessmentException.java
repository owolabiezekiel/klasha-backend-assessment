package com.example.klashabackendassessment.exceptions;

import java.util.List;
import java.util.Map;

public class AssessmentException extends RuntimeException {
  private int code;
  private Map<String, List<String>> responseHeaders;
  private String responseBody;

  public AssessmentException() {
    this.code = 0;
    this.responseHeaders = null;
    this.responseBody = null;
  }

  public AssessmentException(String message) {
    super(message);
    this.code = 0;
    this.responseHeaders = null;
    this.responseBody = null;
  }

  public AssessmentException(String message, int code) {
    super(message);
    this.code = code;
    this.responseHeaders = null;
    this.responseBody = null;
  }

  public int getCode() {
    return this.code;
  }
}
