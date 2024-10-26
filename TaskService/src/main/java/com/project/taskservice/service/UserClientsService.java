package com.project.taskservice.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

public interface UserClientsService {

   public HttpStatusCode isExistUsersById(List<String> idList); 

}