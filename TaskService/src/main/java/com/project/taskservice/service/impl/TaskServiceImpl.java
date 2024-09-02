package com.project.taskservice.service.impl;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.taskservice.model.Task;
import com.project.taskservice.repository.TaskRepository;
import com.project.taskservice.service.ProjectClientService;
import com.project.taskservice.service.TaskService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService{
    
    private final ProjectClientService clientService;
    private final TaskRepository repository;

    @Override
    public Task createTask(Task task) {

        ResponseEntity entity =  clientService.isExistById(task.getProjectId());

        if (entity != null) {
            return repository.save(task);
        }

        return null;
    }

    @Override
    public List<Task> getAll() {
        return repository.findAll();
    }

}
