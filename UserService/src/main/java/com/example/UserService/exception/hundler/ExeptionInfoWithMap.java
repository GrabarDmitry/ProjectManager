package com.example.UserService.exception.hundler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class ExeptionInfoWithMap {
    private String httpStatusCode;
    private String httpStatusType;
    private Map<String, String> messages;

    public ExeptionInfoWithMap(HttpStatus httpStatus, Map<String, String> messages) {
        this.httpStatusCode = String.valueOf(httpStatus.value());
        this.httpStatusType = httpStatus.getReasonPhrase();
        this.messages = messages;
    }
}
