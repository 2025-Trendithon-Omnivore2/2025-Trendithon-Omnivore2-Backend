package com.example.test.omnivore2trendithon2025.cake.api.dto.response;

import com.example.test.omnivore2trendithon2025.cake.domain.CakeColor;
import com.example.test.omnivore2trendithon2025.cake.domain.cakecandle.api.dto.response.CakeCandleResponse;
import lombok.Builder;

import java.util.List;

@Builder
public record CakeResponse(
        CakeColor color, List<CakeCandleResponse> candles) {
}
