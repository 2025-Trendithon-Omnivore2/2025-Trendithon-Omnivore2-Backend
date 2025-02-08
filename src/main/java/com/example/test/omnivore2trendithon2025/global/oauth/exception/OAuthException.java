package com.example.test.omnivore2trendithon2025.global.oauth.exception;


import com.example.test.omnivore2trendithon2025.global.error.exception.AuthGroupException;

public class OAuthException extends AuthGroupException {
    public OAuthException(String message) {
        super(message);
    }

    public OAuthException() {
        this("OAuth 서버와의 통신 과정에서 문제가 발생했습니다.");
    }
}
