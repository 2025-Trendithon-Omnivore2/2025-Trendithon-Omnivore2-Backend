package com.example.test.omnivore2trendithon2025.cake.exception;

import com.example.test.omnivore2trendithon2025.global.error.exception.NotFoundGroupException;

public class CakeNotFoundException extends NotFoundGroupException {
    public CakeNotFoundException(String message) { super(message); }

    public CakeNotFoundException() { this("케이크를 찾을 수 없습니다."); }
}
