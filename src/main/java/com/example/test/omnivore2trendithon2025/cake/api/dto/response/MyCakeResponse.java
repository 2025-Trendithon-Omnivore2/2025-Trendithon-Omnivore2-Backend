package com.example.test.omnivore2trendithon2025.cake.api.dto.response;

import com.example.test.omnivore2trendithon2025.cake.domain.CakeColor;
import com.example.test.omnivore2trendithon2025.cake.domain.cakecandle.api.dto.response.CakeCandleResponse;
import com.example.test.omnivore2trendithon2025.cake.domain.cakecandle.domain.CakeCandle;
import lombok.Builder;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Builder
public record MyCakeResponse(
        Long cakeId,
        CakeColor color,
        List<CakeCandleResponse> candles,
        Integer likeCount) {

    public static MyCakeResponse of(Long cakeId, CakeColor color,
                                    List<CakeCandle> candles, Integer likeCount) {
        return MyCakeResponse.builder()
                .cakeId(cakeId)
                .color(color)
                .candles(Optional.ofNullable(candles)
                        .orElse(Collections.emptyList())
                        .stream()
                        .filter(Objects::nonNull)
                        .map(candle -> CakeCandleResponse.builder()
                                .candleId(candle.getId())
                                .imgUrl(candle.getImgUrl())
                                .content(candle.getContent())
                                .build())
                        .collect(Collectors.toList()))
                .likeCount(likeCount)
                .build();
    }
}
