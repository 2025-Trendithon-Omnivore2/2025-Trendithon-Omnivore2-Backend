package com.example.test.omnivore2trendithon2025.cupcake.api.dto.response;

import com.example.test.omnivore2trendithon2025.cupcake.domain.AccessRange;
import com.example.test.omnivore2trendithon2025.cupcake.domain.CupCake;
import com.example.test.omnivore2trendithon2025.cupcake.domain.Emotion;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record CupCakeResponse(
        Long cupCakeId,
        Emotion emotion,
        String content,
        String nickname,
        LocalDateTime date,
        AccessRange accessRange,
        Integer likeCount,
        boolean like
) {
    public static CupCakeResponse of(Long cupCakeId, Emotion emotion, String content, String nickname, LocalDateTime date, AccessRange accessRange, Integer likeCount, boolean like) {
        return CupCakeResponse.builder()
                .cupCakeId(cupCakeId)
                .emotion(emotion)
                .content(content)
                .nickname(nickname)
                .date(date)
                .accessRange(accessRange)
                .likeCount(likeCount)
                .like(like)
                .build();
    }
}
