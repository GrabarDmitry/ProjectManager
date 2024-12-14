package com.project.taskservice.service;

import java.util.Date;
import java.util.List;

import com.project.taskservice.model.Task;

public interface TaskService {

    public Task createTask(Task task); 

    public Task getById(Long id); 

    public List<Task> getAllWithFilter(String status,String userId,Long projectId,Date dateEnd,String title);

    public Task update(Task task);

    public void delete(Long id);

 }
 
