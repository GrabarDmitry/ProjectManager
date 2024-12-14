package com.project.projectservice.model;

import com.project.projectservice.model.enums.ProjectStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private String managerId;

    private Date dateStart;

    private Date dateEnd;

    @Enumerated(EnumType.STRING)
    private ProjectStatus status;

}