package com.project.taskservice.model;

import org.springframework.data.redis.core.RedisHash;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@RedisHash("project")
public class Project {
    
    @Id
    private Long id;

}
