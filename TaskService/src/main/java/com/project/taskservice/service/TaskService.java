package com.project.taskservice.service;

import java.util.List;

import com.project.taskservice.model.Task;

public interface TaskService {

    public Task createTask(Task task); 

    public Task getById(Long id); 

    public List<Task> getAllWithFilter(String status,String userId,Long projectId,String dateEnd,String title);
 
    public List<Task> getAllByProjectId(Long projectId);

    public Task update(Task task);

    public void delete(Long id);

 }
 
