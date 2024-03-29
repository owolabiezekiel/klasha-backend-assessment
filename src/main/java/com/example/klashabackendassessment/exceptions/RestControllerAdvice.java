package com.example.klashabackendassessment.exceptions;

import static java.util.Collections.singletonList;
import static org.springframework.http.HttpStatus.*;

import com.example.klashabackendassessment.app.model.ResponseModel;
import com.example.klashabackendassessment.utils.ErrorFormatter;
import java.util.concurrent.CompletionException;
import javax.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j
public class RestControllerAdvice extends ResponseEntityExceptionHandler {
  @ExceptionHandler(value = {CompletionException.class})
  public ResponseEntity<ResponseModel> handleCompletionException(CompletionException ex) {
    if (ex.getCause() instanceof AssessmentException) {
      return handleDomainException((AssessmentException) ex.getCause());
    }
    return handleException(ex);
  }

  @ExceptionHandler(value = {AssessmentException.class})
  public ResponseEntity<ResponseModel> handleDomainException(AssessmentException ex) {
    log.error("Unhandled exception encountered: {}", ex.getMessage(), ex);
    return new ResponseEntity<>(
        new ResponseModel(null, "", singletonList(ex.getMessage()), ex.getCode()),
        valueOf(ex.getCode()));
  }

  @ExceptionHandler(value = {Throwable.class})
  public ResponseEntity<ResponseModel> handleException(Throwable ex) {
    log.error("Unhandled exception encountered: {}", ex.getMessage(), ex);
    return new ResponseEntity<>(
        new ResponseModel(null, "", singletonList(ex.getMessage()), INTERNAL_SERVER_ERROR.value()),
        INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(ValidationException.class)
  public ResponseEntity<ResponseModel> handleValidationException(ValidationException ex) {
    log.error("validation error: {}", ex.getMessage());
    return new ResponseEntity<>(
        new ResponseModel(null, "", singletonList(ex.getMessage()), INTERNAL_SERVER_ERROR.value()),
        INTERNAL_SERVER_ERROR);
  }

  @Override
  protected ResponseEntity handleMethodArgumentNotValid(
      MethodArgumentNotValidException ex,
      HttpHeaders headers,
      HttpStatus status,
      WebRequest request) {
    BindingResult bindingResult = ex.getBindingResult();
    return new ResponseEntity<>(
        new ResponseModel(null, "", ErrorFormatter.format(bindingResult), BAD_REQUEST.value()),
        HttpStatus.BAD_REQUEST);
  }
}
