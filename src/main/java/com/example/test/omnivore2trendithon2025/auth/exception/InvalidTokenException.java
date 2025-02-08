package com.example.test.omnivore2trendithon2025.auth.exception;

import com.example.test.omnivore2trendithon2025.global.error.exception.AuthGroupException;

public class InvalidTokenException extends AuthGroupException {
    public InvalidTokenException(String message) {
        super(message);
    }

    public InvalidTokenException() {
        this("토큰이 유효하지 않습니다.");
    }
}
