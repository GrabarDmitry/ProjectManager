package com.project.taskservice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.taskservice.model.Project;

@Repository
public interface ProjectRepositoryRedis extends CrudRepository<Project, Long>{
    
}
