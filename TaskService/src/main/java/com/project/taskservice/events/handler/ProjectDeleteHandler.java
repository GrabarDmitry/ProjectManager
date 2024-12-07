package com.project.taskservice.events.handler;

import java.util.function.Consumer;

import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

import com.project.taskservice.events.model.ProjectEventModel;
import com.project.taskservice.repository.ProjectRepositoryRedis;
import com.project.taskservice.service.ProjectCacheService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProjectDeleteHandler {
 
    private final ProjectCacheService cacheService;

    @Bean
    public Consumer<Message<ProjectEventModel>> projectDeleteInput() {
        return message -> {
            try {
                ProjectEventModel projectEvent = message.getPayload();
                Long projectId = projectEvent.getId();
                cacheService.delete(projectId);
                log.info("Received a deleter project with id: {} " + projectId);
            } catch (Exception e) {
                log.error("Error in reseiver: {}", message, e);
                throw e;
            }
        };
    }

}
