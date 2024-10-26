package com.project.taskservice.service.impl;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.project.taskservice.exception.ResourceException;
import com.project.taskservice.service.ProjectClientService;
import com.project.taskservice.service.UserClientsService;

@Service
public class UserClientServiceImpl implements UserClientsService{
    
    private final RestTemplate restTemplate;

    public UserClientServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public HttpStatusCode isExistUsersById(List<String> idList) {
        try {

            String url = UriComponentsBuilder.fromHttpUrl("http://localhost:8056/api/v1/user/isExistUsers")
            .queryParam("userId", idList.toArray())
            .toUriString();
            
            ResponseEntity<Void> response = restTemplate.getForEntity(url, Void.class);

            return response.getStatusCode();

        } catch (HttpClientErrorException.BadRequest e) {
            throw new ResourceException("You try to add user that doesn't exist");
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new ResourceException("You try to user that doesn't exist");
            }
            throw e;
        }
    }
    
}
