package com.project.projectservice.event.sourse;

import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import com.project.projectservice.event.model.ProjectEventModel;
import com.project.projectservice.model.Project;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class EventSender {
    
    private final StreamBridge streamBridge;

    public void publishProjectDelete(Long projectId){
        log.info("Send in kafka project deleted with id: {}", projectId);
        ProjectEventModel project =  new ProjectEventModel(projectId);

        Message<ProjectEventModel> message = MessageBuilder
         .withPayload(project)
         .build();

        log.info("message INFO : {}", message);

        streamBridge.send("project-delete-output", MessageBuilder.withPayload(project).build());
    }

}
