package com.example.test.omnivore2trendithon2025.cupcake.exception;

import com.example.test.omnivore2trendithon2025.global.error.exception.AccessDeniedGroupException;

public class LowCupCakeAccessRoleException extends AccessDeniedGroupException {
    public LowCupCakeAccessRoleException(String message) {
        super(message);
    }
    public LowCupCakeAccessRoleException() { this("친구가 아니거나, 비공개 컵케이크 입니다."); }
}
