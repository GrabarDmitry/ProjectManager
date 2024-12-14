package com.project.taskservice.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import com.project.taskservice.model.User;

public interface UserClientsService {

   public List<User> getListOfUser(List<String> idList); 

}