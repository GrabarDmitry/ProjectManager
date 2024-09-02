package com.project.taskservice.exception.hundler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

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

}