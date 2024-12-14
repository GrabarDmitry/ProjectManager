package com.project.eventservice.event.hundler;

import java.util.function.Consumer;

import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

import com.project.eventservice.event.model.NotificationType;
import com.project.eventservice.event.model.UserNotification;
import com.project.eventservice.service.MailService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserEventHandler {
 
    private final MailService mailService;

    @Bean
    public Consumer<Message<UserNotification>> userEventInput() {
        return message -> {
            try {
                UserNotification userEvent = message.getPayload();
                log.info("Received a mesage in user notification with user mail {} and type {} ", userEvent.getMail(), userEvent.getType());
                
                if (userEvent.getType() == NotificationType.ADD) {
                    mailService.sendMessage(
                        userEvent.getMail(), 
                        "Hi!You added to task!!!"
                    );
                }

            } catch (Exception e) {
                log.error("Error in reseiver: {}", message, e);
                throw e;
            }
        };
    }

}
