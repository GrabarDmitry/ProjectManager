package com.project.projectservice.model;

import com.project.projectservice.model.enums.ProjectStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private Long managerId;

    private Date dateStart;

    private Date dateEnd;

    @Enumerated(EnumType.STRING)
    private ProjectStatus status;

}