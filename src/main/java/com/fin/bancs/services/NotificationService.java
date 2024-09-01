package com.fin.bancs.services;

import java.util.ArrayList;
import java.util.List;

import com.fin.bancs.common.Notifications;
import com.fin.bancs.mapper.NotificationMapper;
import com.fin.bancs.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    @Autowired
    private NotificationMapper notificationMapper;

    @Autowired
    private NotificationRepository notificationRepository;
    
    public void saveNotification(Notifications notification) {
        notificationRepository.save(notification);
    }

    public List<Notifications> getActiveNotifications() {
        List<Notifications> activeNotifications = new ArrayList<>();
        activeNotifications = notificationRepository.findByIsActive(1);
        return activeNotifications;
    }
}
