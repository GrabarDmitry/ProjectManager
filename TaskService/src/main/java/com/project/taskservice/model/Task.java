package com.project.taskservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Task {
    Long id;
    Long projectId;
}
