package com.project.taskservice.events.source;

import java.util.List;

import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import com.project.taskservice.events.model.UserNotification;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class EventSender {
    
    private final StreamBridge streamBridge;

    public void publishUserNotification(List<UserNotification> notifications){
        notifications.stream().forEach(notification->{
            log.info("Send in kafka add user to project with mail: {}", notification.getMail());
    
            Message<UserNotification> message = MessageBuilder
             .withPayload(notification)
             .build();
    
            streamBridge.send("user-notification-output", message);
        });
    }

}