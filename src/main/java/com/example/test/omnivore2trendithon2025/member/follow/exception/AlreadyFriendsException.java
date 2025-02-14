package com.example.test.omnivore2trendithon2025.member.follow.exception;

import com.example.test.omnivore2trendithon2025.global.error.exception.AccessDeniedGroupException;

public class AlreadyFriendsException extends AccessDeniedGroupException {
    public AlreadyFriendsException(String message) {
        super(message);
    }

    public AlreadyFriendsException() {
        this("이미 친구를 요청을 수락했습니다.");
    }
}
