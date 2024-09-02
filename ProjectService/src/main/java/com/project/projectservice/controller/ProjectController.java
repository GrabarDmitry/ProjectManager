package com.project.projectservice.controller;

import com.project.projectservice.model.Project;
import com.project.projectservice.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/project")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping
    public Project createProject(@RequestBody Project project){
        return projectService.createProject(project);
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
