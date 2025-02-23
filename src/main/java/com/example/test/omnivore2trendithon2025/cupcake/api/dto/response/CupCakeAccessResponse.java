package com.example.test.omnivore2trendithon2025.cupcake.api.dto.response;

import com.example.test.omnivore2trendithon2025.cupcake.domain.AccessRange;
import com.example.test.omnivore2trendithon2025.cupcake.domain.Emotion;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record CupCakeAccessResponse(
        Long cupCakeId,
        Emotion emotion,
        LocalDateTime date,
        AccessRange accessRange
) {
}
