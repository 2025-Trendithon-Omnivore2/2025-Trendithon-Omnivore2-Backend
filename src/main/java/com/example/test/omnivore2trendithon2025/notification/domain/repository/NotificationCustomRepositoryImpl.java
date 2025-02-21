package com.example.test.omnivore2trendithon2025.notification.domain.repository;

import static com.example.test.omnivore2trendithon2025.member.domain.QMember.member;
import static com.example.test.omnivore2trendithon2025.notification.domain.QNotification.notification;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NotificationCustomRepositoryImpl implements NotificationCustomRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public void markAllAsRead(Long memberId) {
        queryFactory.update(notification)
                .set(notification.isRead, true)
                .where(notification.receiver.id.eq(memberId))
                .execute();
    }
}
