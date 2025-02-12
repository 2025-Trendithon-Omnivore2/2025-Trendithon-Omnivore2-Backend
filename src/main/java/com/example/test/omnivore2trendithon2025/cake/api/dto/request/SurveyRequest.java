package com.example.test.omnivore2trendithon2025.cake.api.dto.request;

import com.example.test.omnivore2trendithon2025.cake.domain.Answers;

import java.util.List;

public record SurveyRequest(
        Long memberId, String answer1, String answer2, String answer3
) {
}
