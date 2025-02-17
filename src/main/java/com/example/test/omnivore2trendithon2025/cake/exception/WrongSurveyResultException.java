package com.example.test.omnivore2trendithon2025.cake.exception;

import com.example.test.omnivore2trendithon2025.global.error.exception.NotFoundGroupException;

public class WrongSurveyResultException extends NotFoundGroupException {
    public WrongSurveyResultException(String message) {
        super(message);
    }

    public WrongSurveyResultException() {this("잘못된 설문조사 결과입니다.");}
}
