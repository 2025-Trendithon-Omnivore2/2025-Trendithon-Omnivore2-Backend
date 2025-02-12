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
        String profileImage,
        boolean isFollow
) {
    public static FollowInfoResDto of(Follow follow, Long myMemberId) {
        Member friend = follow.getToMember().getId().equals(myMemberId)
                ? follow.getFromMember()
                : follow.getToMember();
        boolean isFollow = follow.getFollowStatus().equals(FollowStatus.ACCEPT);

        return FollowInfoResDto.builder()
                .memberId(friend.getId())
                .name(friend.getName())
                .profileImage(friend.getPicture())
                .isFollow(isFollow)
                .build();
    }
}
