package com.project.projectservice.controller.dto.response;

import java.util.Date;

import com.project.projectservice.model.enums.ProjectStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectResponseDTO {
    
    private Long id;

    private String title;

    private String description;

    private String managerId;

    private Date dateStart;

    private Date dateEnd;

    private ProjectStatus status;

}
