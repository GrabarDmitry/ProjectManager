package com.project.taskservice.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.project.taskservice.exception.ResourceException;
import com.project.taskservice.service.ProjectClientService;

@Service
public class ProjectClientServiceImpl implements ProjectClientService{
    
    private final RestTemplate restTemplate;

    public ProjectClientServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public ResponseEntity<Void> isExistById(Long projectId) {
        try {
            ResponseEntity<Void> response = restTemplate.getForEntity(
                    "http://localhost:8081/api/v1/project/" + projectId, Void.class);

            return response;

        } catch (HttpClientErrorException.BadRequest e) {
            throw new ResourceException("You try to add task to project that doesn't exist: projectId " + projectId);
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new ResourceException("You try to add task to project that doesn't exist: projectId " + projectId);
            }
            throw e;
        }
    }
    
}
