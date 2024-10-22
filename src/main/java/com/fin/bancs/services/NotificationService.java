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
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    @Autowired
    private NotificationMapper notificationMapper;
    @Autowired
    private SequenceGenerator sequenceGenerator;

    @Autowired
    private NotificationRepository notificationRepository;

    //@CacheEvict(value = "notifications", allEntries = true)
    @CachePut(value = "notifications", key = "#notification.ntfctnId")
    public void saveNotification(Notifications notification) {
        BigInteger ntfctnId = sequenceGenerator.generateSequence("NtfctnId_Seq");
        notification.setNtfctnId(ntfctnId.toString());
        notificationRepository.save(notification);
    }

    @Cacheable(value = "notifications", key = "'activeNotifications'")
    public List<Notifications> getActiveNotifications() {
        List<Notifications> activeNotifications;
        activeNotifications = notificationRepository.findByIsActive(1);
        return activeNotifications;
    }

    @CacheEvict(value = "notifications", allEntries = true)
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
