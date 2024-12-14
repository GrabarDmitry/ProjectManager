package com.example.UserService.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.UserService.model.User;
import com.example.UserService.service.UserService;

import lombok.RequiredArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    
    private final UserService service;

    @GetMapping
    public ResponseEntity<List<User>> getAll() {
        return new ResponseEntity<>(
            service.getAll(),
            HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable("userId") String userId){
        return new ResponseEntity<>(
        service.getByUserId(userId),
            HttpStatus.OK);
    }

    @GetMapping("/listUsers")
    public ResponseEntity<List<User>> getUsersById(@RequestParam List<String> userId) {
        return new ResponseEntity<>(
            service.listUserById(userId),
            HttpStatus.OK);
    }   

}
