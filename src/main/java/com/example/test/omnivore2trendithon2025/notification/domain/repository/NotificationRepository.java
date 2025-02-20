package com.example.test.omnivore2trendithon2025.notification.domain.repository;

import com.example.test.omnivore2trendithon2025.member.domain.Member;
import com.example.test.omnivore2trendithon2025.notification.domain.Notification;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

    List<Notification> findAllByReceiver(Member receiver);
}
