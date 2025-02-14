package com.example.test.omnivore2trendithon2025.member.follow.api.dto.request;

import com.example.test.omnivore2trendithon2025.member.domain.Member;
import com.example.test.omnivore2trendithon2025.member.follow.domain.Follow;
import com.example.test.omnivore2trendithon2025.member.follow.domain.FollowStatus;

public record FollowReqDto(
        String email
) {
    public Follow toEntity(Member fromMember, Member toMember) {
        return Follow.builder()
                .fromMember(fromMember)
                .toMember(toMember)
                .followStatus(FollowStatus.WAIT)
                .build();
    }
}
