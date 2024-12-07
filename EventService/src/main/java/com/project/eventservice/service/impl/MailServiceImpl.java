package com.project.eventservice.service.impl;

import org.springframework.stereotype.Service;

import com.project.eventservice.service.MailService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MailServiceImpl implements MailService{
    
    public void sendMessage(String mail ,String text){
        log.info("Sent message om mail {}, text:{}", mail, text);
    }

}
