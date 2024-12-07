package com.project.taskservice.service.impl;

import java.util.LinkedList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.taskservice.events.model.NotificationType;
import com.project.taskservice.events.model.UserNotification;
import com.project.taskservice.events.source.EventSender;
import com.project.taskservice.model.Task;
import com.project.taskservice.repository.TaskRepository;
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

        List<UserNotification> notifications = new LinkedList<>();
 
        notifications.add(new UserNotification("TEST@MAIL.COM", NotificationType.ADD));
        notifications.add(new UserNotification("gleB@MAIL.COM", NotificationType.ADD));

        eventSender.publishUserNotification(notifications);

        boolean isExistProject =  projectClientService.isExistById(task.getProjectId());
        HttpStatusCode isExistUsers = userClientsService.isExistUsersById(task.getUsers());




        if (isExistProject && isExistUsers.value() == 200) {
            return repository.save(task);
        }

        return null;
    }

    @Override
    public List<Task> getAll() {
        return repository.findAll();
    }

}
