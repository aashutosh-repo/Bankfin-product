package com.fin.bancs.services;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.fin.bancs.common.Notifications;
import com.fin.bancs.mapper.NotificationMapper;
import com.fin.bancs.repository.NotificationRepository;
import com.fin.bancs.utils.SequenceGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    @Autowired
    private NotificationMapper notificationMapper;
    @Autowired
    private SequenceGenerator sequenceGenerator;

    @Autowired
    private NotificationRepository notificationRepository;
    
    public void saveNotification(Notifications notification) {
        BigInteger ntfctnId = sequenceGenerator.generateSequence("NtfctnId_Seq");
        notification.setNtfctnId(ntfctnId.toString());
        notificationRepository.save(notification);
    }

    public List<Notifications> getActiveNotifications() {
        List<Notifications> activeNotifications;
        activeNotifications = notificationRepository.findByIsActive(1);
        return activeNotifications;
    }
    public boolean deleteNotification(String notificationId){
        Optional<Notifications> notification = notificationRepository.findById(notificationId);
        if(notification.isPresent()){
            notification.get().setIsActive(2);
            notificationRepository.save(notification.get());
            return true;
        }
        return false;
    }
}
