package com.project.taskservice.service.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.core.task.TaskExecutor;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

import com.project.taskservice.events.model.NotificationType;
import com.project.taskservice.events.model.UserNotification;
import com.project.taskservice.events.source.EventSender;
import com.project.taskservice.exception.ResourceException;
import com.project.taskservice.model.Project;
import com.project.taskservice.model.Task;
import com.project.taskservice.model.User;
import com.project.taskservice.repository.TaskRepository;
import com.project.taskservice.repository.specification.TaskSpecification;
import com.project.taskservice.service.ProjectClientService;
import com.project.taskservice.service.TaskService;
import com.project.taskservice.service.UserClientsService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService{
    
    private final ProjectClientService projectClientService;
    private final UserClientsService userClientsService;
    private final TaskRepository repository;
    private final EventSender eventSender;


    @Override
    public Task createTask(Task task) {
        log.info("Service method called to create Task : {}", task);
        
        List<UserNotification> notifications = new ArrayList<>();

        Project project =  projectClientService.geProjectById(task.getProjectId());
        List<User> listOfUser = userClientsService.getListOfUser(task.getUsers());

        listOfUser.
            stream().
            forEach(
                t -> notifications.add(new UserNotification(t.getEMAIL(), NotificationType.ADD))
            );
        
        eventSender.publishUserNotification(notifications);

        return repository.save(task);
    }

    @Override
    public Task getById(Long id) {
        log.info("Service method called to find Task with id: {}", id);
        return repository
            .findById(id)
            .orElseThrow(
                () -> {
                  log.warn("Task with Id: {} not found", id);
                  throw new ResourceException("Task with Id: " + id + " not found");
                });
    }

    @Override
    public List<Task> getAllWithFilter(String status,String userId,Long projectId,java.util.Date dateEnd,String title){
        log.info("Service method called to find all tasks");
        return repository.findAll(TaskSpecification.withFilters(status, userId, projectId, dateEnd, title));
    }

    @Override
    public Task update(Task task) {
        log.info("Service method called to update Task: {}", task);
        repository
            .findById(task.getId())
            .ifPresentOrElse(
                u -> {
                    repository.saveAndFlush(task);
                },
                () -> {
                    log.error("Task with Id: {} not found", task.getId());
                    throw new ResourceException(
                        "Task with Id: " + task.getId() + " not found");
                    }
                );
        return task;
    }

    @Override
    public void delete(Long id){
        log.info("Service method called to delete Task with id: {}", id);
        repository.deleteById(id);
    }

}
