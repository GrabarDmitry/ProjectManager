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

    @GetMapping("/{userId}")
    User getProjectById(@PathVariable("userId") String userId){
        return service.getByUserId(userId);
    }

    @GetMapping("/isExistUsers")
    public ResponseEntity<Void> getUsersById(@RequestParam List<String> userId) {
        boolean usersExist = service.areUsersExist(userId);
    
        if (usersExist) {
            return ResponseEntity.status(HttpStatus.OK).build(); 
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }   

}
