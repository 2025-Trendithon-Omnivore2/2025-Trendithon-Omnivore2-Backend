package com.example.test.omnivore2trendithon2025.cupcake.api.dto.request;

import com.example.test.omnivore2trendithon2025.cupcake.domain.AccessRange;
import com.example.test.omnivore2trendithon2025.cupcake.domain.Emotion;

public record SaveCupCakeRequest(
        Emotion emotion,
        String content,
        AccessRange accessRange
) {
}
