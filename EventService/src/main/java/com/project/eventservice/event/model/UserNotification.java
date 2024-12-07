package com.project.eventservice.event.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserNotification {
    
    @JsonProperty("mail")
    private String mail;

    @JsonProperty("type")
    private NotificationType type;

}
