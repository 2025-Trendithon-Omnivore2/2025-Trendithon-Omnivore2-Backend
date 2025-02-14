package com.example.test.omnivore2trendithon2025.member.follow.api.dto.response;

import com.example.test.omnivore2trendithon2025.member.domain.Member;
import lombok.Builder;

@Builder
public record FollowResDto(
        Long toMemberId
) {
    public static FollowResDto from(Member member) {
        return FollowResDto.builder()
                .toMemberId(member.getId())
                .build();
    }
}
