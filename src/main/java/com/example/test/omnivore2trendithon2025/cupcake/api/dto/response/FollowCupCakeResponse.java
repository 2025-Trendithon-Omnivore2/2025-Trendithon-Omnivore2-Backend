package com.example.test.omnivore2trendithon2025.cupcake.api.dto.response;

import com.example.test.omnivore2trendithon2025.cupcake.domain.AccessRange;
import com.example.test.omnivore2trendithon2025.cupcake.domain.Emotion;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record FollowCupCakeResponse(
        Long cupcakeId,
        String nickname,
        LocalDateTime date,
        AccessRange accessRange,
        Emotion emotion,
        String content,
        Integer likeCount,
        boolean like
) {
    public static FollowCupCakeResponse of(
            Long cupcakeId,
            String nickname,
            LocalDateTime date,
            AccessRange accessRange,
            Emotion emotion,
            String content,
            Integer likeCount,
            boolean like) {

        return FollowCupCakeResponse.builder()
                .cupcakeId(cupcakeId)
                .nickname(nickname)
                .date(date)
                .accessRange(accessRange)
                .emotion(emotion)
                .content(content)
                .likeCount(likeCount)
                .like(like)
                .build();
    }
}
