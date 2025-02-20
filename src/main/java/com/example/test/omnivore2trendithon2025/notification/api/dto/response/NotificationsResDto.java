package com.example.test.omnivore2trendithon2025.notification.api.dto.response;

import com.example.test.omnivore2trendithon2025.notification.domain.Notification;
import java.util.List;
import lombok.Builder;

@Builder
public record NotificationsResDto(
        List<NotificationResDto> notifications
) {
    public static NotificationsResDto from(List<Notification> notifications) {
        return NotificationsResDto.builder()
                .notifications(notifications.stream()
                        .map((notification) -> NotificationResDto.of(notification.getMessage(), notification.getIsRead()))
                        .toList())
                .build();
    }

    @Builder
    private record NotificationResDto(
            String message,
            Boolean isRead
    ) {
        public static NotificationResDto of(String message, Boolean isRead) {
            return new NotificationResDto(message, isRead);
        }
    }
    
}
