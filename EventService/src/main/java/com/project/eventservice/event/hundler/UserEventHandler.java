package com.project.eventservice.event.hundler;

import java.util.function.Consumer;

import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

import com.project.eventservice.event.model.UserNotification;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserEventHandler {
 
    @Bean
    public Consumer<Message<UserNotification>> userEventInput() {
        return message -> {
            try {
                log.info(message.getPayload().toString());
                UserNotification userEvent = message.getPayload();
                log.info("Received a mesage in user notification with user mail {} and type {} ", userEvent.getMail(), userEvent.getType());
                log.info(userEvent.getMail());
            } catch (Exception e) {
                log.error("Error in reseiver: {}", message, e);
                throw e;
            }
        };
    }

}
