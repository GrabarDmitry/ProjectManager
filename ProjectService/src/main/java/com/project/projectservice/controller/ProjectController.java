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
import java.util.stream.Collectors;

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

    @GetMapping("/{id}")
    public ResponseEntity<ProjectResponseDTO> getById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(
            dtoConverter.toDTO(
                projectService.getProjectById(id)
                ),
            HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ProjectResponseDTO>> getAll() {
        return new ResponseEntity<>(
            projectService.getAll()
                        .stream()
                        .map(dtoConverter::toDTO)
                        .collect(Collectors.toList()),
            HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjectResponseDTO> updateProject(
        @PathVariable("id") Long id, @RequestBody @Valid ProjectRequestDTO requestDTO) {
        return new ResponseEntity<>(
            dtoConverter.toDTO(
                projectService.updateProject(
                    dtoConverter.toEntityUpdate(id, requestDTO))),
            HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(
        @PathVariable("id") Long id) {
            projectService.deleteProject(id);
            return new ResponseEntity<>(HttpStatus.OK);
    }

}
