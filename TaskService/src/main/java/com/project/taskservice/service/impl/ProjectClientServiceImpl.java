package com.project.taskservice.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.project.taskservice.exception.ResourceException;
import com.project.taskservice.model.Project;
import com.project.taskservice.repository.ProjectRepositoryRedis;
import com.project.taskservice.service.ProjectCacheService;
import com.project.taskservice.service.ProjectClientService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProjectClientServiceImpl implements ProjectClientService{
    
    private final RestTemplate restTemplate;
    private final ProjectCacheService cacheService;

    @Override
    public boolean isExistById(Long projectId) {
        Project project = cacheService.getById(projectId);

        if (project != null){
            log.info("Get projrct form cache, project id: {}", projectId);
            return true;
        }

        log.warn("Project with id {} not found in cache", projectId);

        try {
            ResponseEntity<Project> response = restTemplate.getForEntity(
                    "http://localhost:8081/api/v1/project/" + projectId, Project.class);

            Project projectRespone = response.getBody();

            if (projectRespone != null) {
                cacheService.save(projectRespone);
                log.info("Project was found by request to server, project id {}", projectId);
                return true;
            }
        } catch (HttpClientErrorException.BadRequest e) {
            throw new ResourceException("You try to add task to project that doesn't exist: projectId " + projectId);
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new ResourceException("You try to add task to project that doesn't exist: projectId " + projectId);
            }
            throw e;
        }

        return false;
    }
    
}
