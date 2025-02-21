package com.example.test.omnivore2trendithon2025.notification.domain;

import lombok.Getter;

@Getter
public enum NotificationMessage {
    FOLLOW_REQUEST("%s 님으로부터 팔로우 요청이 왔습니다."),
    FOLLOW_ACCEPT("팔로우 요청이 수락되었습니다."),
    FOLLOW_REJECT("팔로우 요청이 거절되었습니다."),
    ;

    private final String message;

    NotificationMessage(String message) {
        this.message = message;
    }
}
