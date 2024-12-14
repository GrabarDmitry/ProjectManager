package com.project.taskservice.service.impl;

import org.springframework.stereotype.Service;

import com.project.taskservice.exception.ResourceException;
import com.project.taskservice.model.Project;
import com.project.taskservice.repository.ProjectRepositoryRedis;
import com.project.taskservice.service.ProjectCacheService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProjectCacheServiceImpl implements ProjectCacheService{
    
    private final ProjectRepositoryRedis repositoryRedis;

    public Project getById(Long id) {
		log.info("Service method called to find Project form cache with id: {}", id);
        return repositoryRedis
        .findById(id)
        .orElseThrow(
            () -> {
              log.warn("Project with Id: {} not found in cache", id);
              throw new ResourceException("Project with Id: " + id + " not found in cache");
            });
	}
	
	public Project save(Project project) {
        log.info("Save Project in cache", project);
        return	repositoryRedis.save(project);
    }

    public void delete(Long id){
        log.info("Delete Project form cache with id", id);
        repositoryRedis.deleteById(id);
    }

}
