package com.example.test.omnivore2trendithon2025.cupcake.api.dto.response;

import com.example.test.omnivore2trendithon2025.cupcake.domain.AccessRange;
import com.example.test.omnivore2trendithon2025.cupcake.domain.CupCake;
import com.example.test.omnivore2trendithon2025.cupcake.domain.Emotion;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record CupCakeResponse(
        Emotion emotion,
        String content,
        LocalDateTime date,
        AccessRange accessRange,
        Integer likeCount
) {
    public static CupCakeResponse of(Emotion emotion, String content, LocalDateTime date, AccessRange accessRange, Integer likeCount) {
        return CupCakeResponse.builder()
                .emotion(emotion)
                .content(content)
                .date(date)
                .accessRange(accessRange)
                .likeCount(likeCount)
                .build();
    }
}
