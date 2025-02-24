package com.example.test.omnivore2trendithon2025.cupcake.exception;

import com.example.test.omnivore2trendithon2025.global.error.exception.InvalidGroupException;

public class AlreadyExistThisDateException extends InvalidGroupException {
    public AlreadyExistThisDateException(String message) {
        super(message);
    }
    public AlreadyExistThisDateException() { this("오늘자 컵케이크가 이미 생성되었습니다."); }
}
