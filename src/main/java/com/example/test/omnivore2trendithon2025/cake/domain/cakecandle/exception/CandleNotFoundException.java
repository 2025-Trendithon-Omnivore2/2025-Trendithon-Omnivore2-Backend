package com.example.test.omnivore2trendithon2025.cake.domain.cakecandle.exception;

import com.example.test.omnivore2trendithon2025.global.error.exception.NotFoundGroupException;

public class CandleNotFoundException extends NotFoundGroupException {
  public CandleNotFoundException(String message) {
    super(message);
  }

  public CandleNotFoundException() { this("케이크 이미지를 찾지 못했습니다."); }
}
