package com.project.projectservice.service.impl;

import com.project.projectservice.exception.ResourceException;
import com.project.projectservice.model.Project;
import com.project.projectservice.model.enums.ProjectStatus;
import com.project.projectservice.repo.ProjectRepository;
import com.project.projectservice.service.ProjectService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.StackWalker.Option;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepo;

    @Override
    public List<Project> getAll() {
        return projectRepo.findAll();
    }

    @Override
    public Project createProject(Project project) {
        project.setStatus(ProjectStatus.planned);
        return projectRepo.save(project);
    }

    @Override
    public Project updateProject(Project project) {
        return null;
    }

    @Override
    public void deleteProject(Long id) {
        projectRepo.deleteById(id);
    }

    @Override
    public Project getProjectById(Long id) {
        log.info("Service method called to find Project with id: {}", id);
        return projectRepo.findById(id).orElseThrow(
            () -> {
                log.warn("Announcement with Id: {} not found", id);
                throw new ResourceException("Announcement with Id: " + id + " not found");
              });
    }

}
