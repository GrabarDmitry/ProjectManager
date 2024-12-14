package com.project.projectservice.service;

import com.project.projectservice.model.Project;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ProjectService {

    public List<Project> getAllWithFilters(String status,Long managerId,Date dateEnd,Date dateStart,String title);

    public Project getProjectById(Long id);

    public Project createProject(Project project);

    public Project updateProject(Project project);

    public void deleteProject(Long id);

}
