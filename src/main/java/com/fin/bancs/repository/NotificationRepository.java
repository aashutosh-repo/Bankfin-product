package com.fin.bancs.repository;

import com.fin.bancs.common.Notifications;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notifications, String> {
    List<Notifications> findByIsActive (int flag);
}
