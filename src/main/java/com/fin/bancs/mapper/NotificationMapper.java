package com.fin.bancs.mapper;

import com.fin.bancs.common.Notifications;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class NotificationMapper {
    private final ObjectMapper objectMapper = new ObjectMapper();

    public String toJson(Notifications notification) throws JsonProcessingException {
        return objectMapper.writeValueAsString(notification);
    }

    public Notifications fromJson(String json) throws JsonProcessingException {
        return objectMapper.readValue(json, Notifications.class);
    }
}
