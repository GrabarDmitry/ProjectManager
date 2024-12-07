package com.project.taskservice.controller.dto.converter;

import org.springframework.stereotype.Component;

import com.project.taskservice.controller.dto.request.TaskRequestDTO;
import com.project.taskservice.controller.dto.response.TaskResponceDTO;
import com.project.taskservice.model.Task;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class TaskDTOConverter {
    
    public Task toEntityCreate(TaskRequestDTO dto){
        return new Task(
            null,
            dto.getTitle(),
            dto.getDescription(),
            dto.getProjectId(),
            dto.getUsers(),
            dto.getDateEnd(),
            dto.getStatus()
        );
    }

    public TaskResponceDTO toDTO(Task task){
        return new TaskResponceDTO(
            task.getId(),
            task.getTitle(),
            task.getDescription(),
            task.getProjectId(),
            task.getUsers(),
            task.getDateEnd(),
            task.getStatus()
        );
    }

}
