package com.project.taskservice.service.impl;

import org.springframework.stereotype.Service;

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
		try {
			return repositoryRedis.findById(id).orElse(null);
		}catch (Exception ex){
            log.error("Error with check is exist project in chche", ex);
			return null;
		}
	}
	
	public Project save(Project project) {
        try {
            return	repositoryRedis.save(project);
        }catch (Exception ex){
            log.error("Error with save project in cache", ex);
        }
        return null;
    }

    public void delete(Long id){
        repositoryRedis.deleteById(id);
    }

}
