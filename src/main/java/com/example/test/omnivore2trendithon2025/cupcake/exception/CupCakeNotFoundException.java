package com.example.test.omnivore2trendithon2025.cupcake.exception;

import com.example.test.omnivore2trendithon2025.global.error.exception.NotFoundGroupException;

public class CupCakeNotFoundException extends NotFoundGroupException {
    public CupCakeNotFoundException(String message) {
        super(message);
    }
    public CupCakeNotFoundException() { this("컵케이크를 찾을 수 없습니다."); }
}
