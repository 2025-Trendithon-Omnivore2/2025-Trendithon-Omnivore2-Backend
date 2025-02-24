package com.example.test.omnivore2trendithon2025.cake.domain.cakecandle.api.dto.response;

import lombok.Builder;

@Builder
public record CakeCandleResponse(
        Long candleId, String imgUrl, String content, Integer candleIndex) {
}
