package com.example.test.omnivore2trendithon2025.member.follow.exception;

import com.example.test.omnivore2trendithon2025.global.error.exception.AccessDeniedGroupException;

public class FollowAlreadyExistsException extends AccessDeniedGroupException {
    public FollowAlreadyExistsException(String message) {
        super(message);
    }

    public FollowAlreadyExistsException() {
        this("이미 친구를 요청했습니다.");
    }
}
