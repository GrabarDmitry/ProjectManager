package com.project.eventservice.service.impl;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.project.eventservice.service.MailService;
import com.project.eventservice.service.SchedulerService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SchedulerServiceImpl implements SchedulerService {
    
    private final MailService mailService;

    @Scheduled(fixedRate = 120000)
    public void sendNotificationToUserOnProject(){
        mailService.sendMessage("dima@test.mail", "Congratulatins!It works!!!");
    }

}
