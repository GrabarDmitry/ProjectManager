package com.example.UserService.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.UserService.exception.ResourceException;
import com.example.UserService.model.User;
import com.example.UserService.repository.UserRepository;
import com.example.UserService.service.UserService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import java.util.List;



@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    
    private final UserRepository userRepo;

    @Override
    public List<User> getAll() {
        log.info("Service method called to find all Users");
        return userRepo.findAll();
    }

    @Override
    public User getByUserId(String userId){
        log.info("Service method called to find User with id: {}", userId);
        return userRepo.findByStringId(userId).orElseThrow(
            () -> {
                log.warn("User with Id: {} not found", userId);
                throw new ResourceException("User with Id: " + userId + " not found");
              });
    }

    @Override
    public List<User> listUserById(List<String> userId){
        log.info("Service method called to find all Users by nedded id: {}", userId);
        List<User> users = userRepo.findByStringIds(userId);
        return users;
    }
}
