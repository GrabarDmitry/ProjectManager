package com.project.projectservice.controller;

import com.project.projectservice.controller.converter.ProjectDTOConverter;
import com.project.projectservice.controller.dto.request.ProjectRequestDTO;
import com.project.projectservice.controller.dto.response.ProjectResponseDTO;
import com.project.projectservice.model.Project;
import com.project.projectservice.service.ProjectService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/project")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;
    private final ProjectDTOConverter dtoConverter;

    @PostMapping
    public ResponseEntity<ProjectResponseDTO> createProject(@Valid @RequestBody ProjectRequestDTO project){
        return new ResponseEntity<>(
            dtoConverter.toDTO(
                projectService.createProject(dtoConverter.toEntityCreate(project))
                ),
            HttpStatus.CREATED);
    }

    @GetMapping
    public List<Project> getAll(){
        return projectService.getAll();
    }

    @DeleteMapping("/{projectId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteJob(@PathVariable("projectId") Long projectId){
        projectService.deleteProject(projectId);
    }

    @GetMapping("/{projectId}")
    Project getProjectById(@PathVariable("projectId") Long projectId){
        return projectService.getProjectById(projectId);
    }

}
