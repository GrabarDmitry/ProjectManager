package com.example.UserService.service;

import java.util.List;

import com.example.UserService.model.User;

public interface UserService {

    public List<User> getAll();

    public User getByUserId(String userId);

    public boolean areUsersExist(List<String> userId);
    
}
