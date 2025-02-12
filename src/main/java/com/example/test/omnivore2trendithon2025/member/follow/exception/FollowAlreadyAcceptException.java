package com.example.test.omnivore2trendithon2025.member.follow.exception;

import com.example.test.omnivore2trendithon2025.global.error.exception.AccessDeniedGroupException;

public class FollowAlreadyAcceptException extends AccessDeniedGroupException {
    public FollowAlreadyAcceptException(String message) {
        super(message);
    }

    public FollowAlreadyAcceptException() {
        this("이미 친구를 요청을 수락한 상태입니다.");
    }
}
