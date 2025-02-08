package com.example.test.omnivore2trendithon2025.auth.exception;

import com.example.test.omnivore2trendithon2025.global.error.exception.InvalidGroupException;

public class ExistsMemberEmailException extends InvalidGroupException {
    public ExistsMemberEmailException(String message) {
        super(message);
    }

    public ExistsMemberEmailException() {
        this("이미 가입한 계정이 있는 이메일 입니다.");
    }
}
