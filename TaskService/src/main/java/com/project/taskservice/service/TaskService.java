package com.project.taskservice.service;

import java.util.List;

import com.project.taskservice.model.Task;

public interface TaskService {

    public Task createTask(Task task); 

    public List<Task> getAll();
 
 }
 
