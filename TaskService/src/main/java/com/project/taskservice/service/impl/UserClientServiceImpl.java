package com.project.taskservice.service.impl;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.project.taskservice.exception.ResourceException;
import com.project.taskservice.model.User;
import com.project.taskservice.service.UserClientsService;

@Service
public class UserClientServiceImpl implements UserClientsService{
    
    private final String PATH_TO_USER_SERVICE_GET_BY_ID = "http://localhost:8083/api/v1/user/listUsers"; 
    private final RestTemplate restTemplate;

    public UserClientServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<User> getListOfUser(List<String> idList) {
        try {

            String url = UriComponentsBuilder.fromHttpUrl(PATH_TO_USER_SERVICE_GET_BY_ID)
            .queryParam("userId", idList.toArray())
            .toUriString();

            ResponseEntity<List<User>> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<User>>() {}
            );

            List<User> resultResponce = response.getBody();
           
            if (resultResponce.size() != idList.size()) {
                throw new ResourceException("Some users dont exist!");
            }

            return response.getBody();
        }catch (HttpClientErrorException e) {
            throw new ResourceException("Some users dont exist!");
        }
    }
    
}
