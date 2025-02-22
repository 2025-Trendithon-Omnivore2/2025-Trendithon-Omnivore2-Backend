package com.example.test.omnivore2trendithon2025.cupcake.exception;

import com.example.test.omnivore2trendithon2025.global.error.exception.AccessDeniedGroupException;

public class UnauthorizedCupCakeModificationException extends AccessDeniedGroupException {
    public UnauthorizedCupCakeModificationException(String message) {
        super(message);
    }

    public UnauthorizedCupCakeModificationException() { this("컵케이크 접근 범위 수정 권한이 없습니다."); }
}
