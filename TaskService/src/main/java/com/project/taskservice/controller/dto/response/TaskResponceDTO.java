package com.project.taskservice.controller.dto.response;

import java.util.Date;
import java.util.List;

import com.project.taskservice.model.enums.TaskStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskResponceDTO {
     
    private Long id;

    private String title;

    private String description;

    private Long projectId;

    private List<String> users;

    private Date dateEnd;

    private TaskStatus status;
}
