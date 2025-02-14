package com.example.test.omnivore2trendithon2025.member.follow.api.dto.response;

import com.example.test.omnivore2trendithon2025.member.domain.Member;
import com.example.test.omnivore2trendithon2025.member.follow.domain.Follow;
import com.example.test.omnivore2trendithon2025.member.follow.domain.FollowStatus;
import lombok.Builder;

@Builder
public record FollowInfoResDto(
        Long memberId,
        String nickname,
        String name,
        String profileImage
) {
    public static FollowInfoResDto of(Follow follow, Long myMemberId) {
        Member friend = follow.getToMember().getId().equals(myMemberId)
                ? follow.getFromMember()
                : follow.getToMember();

        return FollowInfoResDto.builder()
                .memberId(friend.getId())
                .nickname(friend.getNickname())
                .name(friend.getName())
                .profileImage(friend.getPicture())
                .build();
    }
}
