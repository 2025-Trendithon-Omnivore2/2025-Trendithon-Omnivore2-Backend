package com.example.test.omnivore2trendithon2025.member.follow.api.dto.response;

import com.example.test.omnivore2trendithon2025.member.domain.Member;
import lombok.Builder;

@Builder
public record MemberInfoForFollowResDto(
        Long memberId,
        String name,
        String profileImage,
        boolean isFollow
) {
    public static MemberInfoForFollowResDto of(Member member, boolean isFollow) {
        return MemberInfoForFollowResDto.builder()
                .memberId(member.getId())
                .name(member.getName())
                .profileImage(member.getPicture())
                .isFollow(isFollow)
                .build();
    }
}
