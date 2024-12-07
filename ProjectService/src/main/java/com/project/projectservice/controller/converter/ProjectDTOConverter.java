package com.project.projectservice.controller.converter;

import org.springframework.stereotype.Component;

import com.project.projectservice.controller.dto.request.ProjectRequestDTO;
import com.project.projectservice.controller.dto.response.ProjectResponseDTO;
import com.project.projectservice.model.Project;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProjectDTOConverter {
    
    public Project toEntityCreate(ProjectRequestDTO project){
        return new Project(
            null,
            project.getTitle(),
            project.getDescription(),
            project.getManagerId(),
            project.getDateStart(),
            project.getDateEnd(),
            project.getStatus()
        );
    }

    public Project toEntityUpdate(Long id ,ProjectRequestDTO project){
        return new Project(
            id,
            project.getTitle(),
            project.getDescription(),
            project.getManagerId(),
            project.getDateStart(),
            project.getDateEnd(),
            project.getStatus()
        );
    }

    public ProjectResponseDTO toDTO(Project project){
        return new ProjectResponseDTO(
            project.getId(),
            project.getTitle(),
            project.getDescription(),
            project.getManagerId(),
            project.getDateStart(),
            project.getDateEnd(),
            project.getStatus()
        );
    }

}
