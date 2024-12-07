package com.project.taskservice.exception.hundler;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.project.taskservice.exception.ResourceException;

@Slf4j
@ControllerAdvice
public class CustomExceptionHandler {

  @ExceptionHandler({
    ResourceException.class
  })
  public ResponseEntity<ExceptionInfo> badCredentialsExceptionHandle(Exception ex) {
    log.error("{}: {}", ex.getClass(), ex.getMessage());
    return ResponseEntity.badRequest()
        .contentType(MediaType.APPLICATION_JSON)
        .body(new ExceptionInfo(HttpStatus.BAD_REQUEST, ex.getMessage()));
  }

  @ExceptionHandler({
    MethodArgumentNotValidException.class
  })
  public ResponseEntity<ExeptionInfoWithMap> validationExceptionHandle(MethodArgumentNotValidException ex) {
    log.error("{}: {}", ex.getClass(), ex.getMessage());
    
    Map<String, String> errors = new HashMap<>();
    ex.getBindingResult().getAllErrors().forEach((error) -> {
        String fieldName = ((FieldError) error).getField();
        String errorMessage = error.getDefaultMessage();
        errors.put(fieldName, errorMessage);
    });
    
    return ResponseEntity.badRequest()
        .contentType(MediaType.APPLICATION_JSON)
        .body(new ExeptionInfoWithMap(HttpStatus.BAD_REQUEST, errors));
  }

}