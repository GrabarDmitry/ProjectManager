package com.example.UserService.service.impl;

import org.springframework.stereotype.Service;

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
        return userRepo.findAll();
    }

}
