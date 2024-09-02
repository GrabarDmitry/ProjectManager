package com.project.projectservice.exception.hundler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.project.projectservice.exception.ResourceException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

}