package com.project.taskservice.service;

import org.springframework.http.ResponseEntity;

public interface ProjectClientService {

   public ResponseEntity isExistById(Long projectId); 

}
