package com.example.test.omnivore2trendithon2025.member.follow.api.dto.response;

import com.example.test.omnivore2trendithon2025.member.domain.Member;
import com.example.test.omnivore2trendithon2025.member.follow.domain.Follow;
import lombok.Builder;

@Builder
public record FollowRequestInfoResDto(
        Long memberId,
        Long followId,
        String nickname,
        String name,
        String profileImage
) {
    public static FollowRequestInfoResDto of(Follow follow, Long myMemberId) {
        Member friend = follow.getToMember().getId().equals(myMemberId)
                ? follow.getFromMember()
                : follow.getToMember();

        return FollowRequestInfoResDto.builder()
                .memberId(friend.getId())
                .followId(follow.getId())
                .nickname(friend.getNickname())
                .name(friend.getName())
                .profileImage(friend.getPicture())
                .build();
    }
}
