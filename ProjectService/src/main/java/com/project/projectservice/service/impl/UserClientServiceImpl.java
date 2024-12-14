package com.project.projectservice.service.impl;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.project.projectservice.exception.ResourceException;
import com.project.projectservice.model.Project;
import com.project.projectservice.model.User;
import com.project.projectservice.service.UserClientsService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserClientServiceImpl implements UserClientsService{
    
    private final String PATH_TO_USER_IS_EXIST = "http://localhost:8083/api/v1/user/";
    private final RestTemplate restTemplate;

    public UserClientServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public User getUserById(String userId) {
        try {
            ResponseEntity<User> response = restTemplate.getForEntity(
                PATH_TO_USER_IS_EXIST + userId, User.class);

            User responseUser = response.getBody();

            if (responseUser != null) {
                log.info("User was found by request to server, user id {}", userId);
                return responseUser;
            }
        } catch (HttpClientErrorException e) {
            throw new ResourceException("User doesn't exist: user id " + userId);
        }
        return null;
    }
    
}
