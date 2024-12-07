package com.project.taskservice.service;

import com.project.taskservice.model.Project;

public interface ProjectCacheService {
    
    Project save(Project project);

    Project getById(Long id);

    void delete(Long id);

}
