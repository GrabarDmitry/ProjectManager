package com.project.taskservice.controller;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.taskservice.controller.dto.converter.TaskDTOConverter;
import com.project.taskservice.controller.dto.request.TaskRequestDTO;
import com.project.taskservice.controller.dto.response.TaskResponceDTO;
import com.project.taskservice.service.TaskService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/task")
@RequiredArgsConstructor
public class TaskController {

    private final TaskDTOConverter converter;
    private final TaskService taskService;

    @GetMapping
    public ResponseEntity<List<TaskResponceDTO>> getAll(
        @RequestParam(name = "status", required = false) String status,
        @RequestParam(name = "userId", required = false) String userId,
        @RequestParam(name = "projectId", required = false) Long projectId,
        @RequestParam(name = "dateEnd", required = false) Date dateEnd,
        @RequestParam(name = "title", required = false) String title
    ) {
        return new ResponseEntity<>(
            taskService.getAllWithFilter(status, userId, projectId, dateEnd, title)
                        .stream()
                        .map(converter::toDTO)
                        .collect(Collectors.toList()),
            HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TaskResponceDTO>  createTask(@Valid @RequestBody TaskRequestDTO task){
        return new ResponseEntity<>(
            converter.toDTO(
                taskService.createTask(converter.toEntityCreate(task))
                ),
            HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponceDTO> getById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(
            converter.toDTO(
                taskService.getById(id)
                ),
            HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponceDTO> updateTask(
        @PathVariable("id") Long id, @RequestBody @Valid TaskRequestDTO requestDTO) {
        return new ResponseEntity<>(
            converter.toDTO(
                taskService.update(
                    converter.toEntityUpdate(id, requestDTO))),
            HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(
        @PathVariable("id") Long id) {
            taskService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
    }

}
