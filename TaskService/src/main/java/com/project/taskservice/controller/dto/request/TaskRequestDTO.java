package com.project.taskservice.controller.dto.request;

import java.util.Date;
import java.util.List;

import com.project.taskservice.model.enums.TaskStatus;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskRequestDTO {
     
    private Long id;

    @NotNull(message = "Title of title should not be null!")
    @NotEmpty(message = "Title of title cant be empty!")
    private String title;

    @NotNull(message = "Description of title should not be null!")
    @NotEmpty(message = "Description of title cant be empty!")
    private String description;

    @NotNull(message = "Project should not be null!")
    private Long projectId;

    private List<String> users;

    private Date dateEnd;

    private TaskStatus status;
}
