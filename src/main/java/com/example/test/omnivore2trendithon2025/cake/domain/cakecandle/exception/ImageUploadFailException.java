package com.example.test.omnivore2trendithon2025.cake.domain.cakecandle.exception;

public class ImageUploadFailException extends RuntimeException {
    public ImageUploadFailException(String message) {
        super(message);
    }

    public ImageUploadFailException() { this("s3 이미지 업로드 중 에러 발생"); }
}
