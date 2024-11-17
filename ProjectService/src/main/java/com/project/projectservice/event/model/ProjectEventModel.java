package com.project.projectservice.event.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProjectEventModel {

    private Long id;
    
    public ProjectEventModel(Long id) {
		super();
		this.id = id;
	}
}
