package com.project.projectservice.service.impl;

import com.project.projectservice.event.sourse.EventSender;
import com.project.projectservice.exception.ResourceException;
import com.project.projectservice.model.Project;
import com.project.projectservice.model.User;
import com.project.projectservice.model.enums.ProjectStatus;
import com.project.projectservice.repo.ProjectRepository;
import com.project.projectservice.repo.specification.ProjectSpecification;
import com.project.projectservice.service.ProjectService;
import com.project.projectservice.service.UserClientsService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepo;
    private final EventSender eventSender;
    private final UserClientsService clientsService;

    @Override
    public List<Project> getAllWithFilters(String status,Long managerId,Date dateEnd,Date dateStart,String title) {
        log.info("Service method called to find all projects");
        return projectRepo.findAll(ProjectSpecification.withFilters(status, status, dateEnd, dateStart, title));
    }

    @Override
    public Project createProject(Project project) {
        log.info("Service method called to create Project: {}", project);
        User user = clientsService.getUserById(project.getManagerId().toString());
        project.setStatus(ProjectStatus.planned);
        return projectRepo.save(project);
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    @Override
    public Project updateProject(Project project) {
        log.info("Service method called to update Project: {}", project);
        projectRepo
            .findById(project.getId())
            .ifPresentOrElse(
                u -> {
                    projectRepo.saveAndFlush(project);
                },
                () -> {
                    log.error("Project with Id: {} not found", project.getId());
                    throw new ResourceException(
                        "Project with Id: " + project.getId() + " not found");
                });
        return project;
    }

    @Override
    public void deleteProject(Long id) {
        log.info("Service method called to delete Project with id: {}", id);
        eventSender.publishProjectDelete(id);
        projectRepo.deleteById(id);
    }

    @Override
    public Project getProjectById(Long id) {
        log.info("Service method called to find Project with id: {}", id);
        return projectRepo.findById(id).orElseThrow(
            () -> {
                log.warn("Project with Id: {} not found", id);
                throw new ResourceException("Project with Id: " + id + " not found");
              });
    }

}
