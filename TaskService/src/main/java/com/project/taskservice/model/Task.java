package com.project.taskservice.model;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

import com.project.taskservice.model.enums.TaskStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Task {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private Long projectId;

    @ElementCollection
    private List<String> users;

    private Date dateEnd;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;


}
