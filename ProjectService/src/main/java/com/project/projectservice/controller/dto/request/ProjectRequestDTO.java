package com.project.projectservice.controller.dto.request;

import java.util.Date;

import com.project.projectservice.model.enums.ProjectStatus;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProjectRequestDTO {

    private Long id;

    @NotNull(message = "Title of project should not be null!")
    @NotEmpty(message = "Title of project cant be empty!")
    private String title;

    @NotNull(message = "Description of project should not be null!")
    @NotEmpty(message = "Description of project cant be empty!")
    private String description;

    @NotNull(message = "Manager of project should not be null!")
    private String managerId;

    @NotNull(message = "Date start of project should not be null!")
    private Date dateStart;

    private Date dateEnd;

    private ProjectStatus status;

}
