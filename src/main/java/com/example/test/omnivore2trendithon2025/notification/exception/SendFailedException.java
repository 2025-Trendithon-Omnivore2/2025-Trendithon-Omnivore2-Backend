package com.example.test.omnivore2trendithon2025.notification.exception;

public class SendFailedException extends RuntimeException {
    public SendFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}