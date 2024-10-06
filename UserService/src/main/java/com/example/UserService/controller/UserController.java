package com.example.UserService.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.UserService.model.User;
import com.example.UserService.service.UserService;

import lombok.RequiredArgsConstructor;
import java.util.List;


@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    
    private final UserService service;

     @GetMapping
    public List<User> getAll(){
        return service.getAll();
    }

}
