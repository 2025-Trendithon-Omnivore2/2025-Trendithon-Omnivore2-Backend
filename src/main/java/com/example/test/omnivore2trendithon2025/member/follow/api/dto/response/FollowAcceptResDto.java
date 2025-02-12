package com.example.test.omnivore2trendithon2025.member.follow.api.dto.response;

import com.example.test.omnivore2trendithon2025.member.follow.domain.Follow;
import lombok.Builder;

@Builder
public record FollowAcceptResDto(
        Long fromMemberId,
        Long toMemberId
) {
    public static FollowAcceptResDto from(Follow follow) {
        return FollowAcceptResDto.builder()
                .fromMemberId(follow.getFromMember().getId())
                .toMemberId(follow.getToMember().getId())
                .build();
    }
}
