package com.fin.bancs.controller;

import java.util.List;

import com.fin.bancs.common.Notifications;
import com.fin.bancs.services.NotificationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notifications")
public class NotificationController {
    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveNotification(@RequestBody Notifications notification) {
        notificationService.saveNotification(notification);
        return new ResponseEntity<>("Notification saved", HttpStatus.CREATED);
    }

    @GetMapping("/active")
    public ResponseEntity<List<Notifications>> getActiveNotifications() {
        List<Notifications> activeNotifications = notificationService.getActiveNotifications();
        return new ResponseEntity<>(activeNotifications, HttpStatus.OK);
    }
}
