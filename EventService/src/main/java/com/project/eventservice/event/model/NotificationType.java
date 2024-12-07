package com.project.eventservice.event.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum NotificationType {
    @JsonProperty("ADD")
    ADD,
    @JsonProperty("DELETE")
    DELETE;
}
