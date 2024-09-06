package com.fin.bancs.controller;

import java.util.List;

import com.fin.bancs.common.AccountsConstants;
import com.fin.bancs.common.Notifications;
import com.fin.bancs.dto.ResponseDto;
import com.fin.bancs.error.ErrorCode;
import com.fin.bancs.error.ResourceNotFoundException;
import com.fin.bancs.services.NotificationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @PutMapping("/delete")
    public ResponseEntity<ResponseDto> deleteNotification(@RequestParam String notificationId){
        boolean flag = notificationService.deleteNotification(notificationId);
        if(!flag){
            throw new ResourceNotFoundException(ErrorCode.NOTIFICATION_NOT_FOUND);
        }
        return new ResponseEntity<>(new ResponseDto("Deleted SuccessFully",AccountsConstants.MESSAGE_200),HttpStatus.OK);
    }
}
