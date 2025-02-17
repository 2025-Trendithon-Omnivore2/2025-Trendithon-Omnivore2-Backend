package com.example.test.omnivore2trendithon2025.member.mypage.api.response;

import com.example.test.omnivore2trendithon2025.member.domain.Member;
import lombok.Builder;

@Builder
public record MyPageInfoResDto(
        String picture,
        String email,
        String nickName,
        Integer followerCount
) {
    public static MyPageInfoResDto From(Member member, Integer followerCount) {
        return MyPageInfoResDto.builder()
                .picture(member.getPicture())
                .email(member.getEmail())
                .nickName(member.getNickname())
                .followerCount(followerCount)
                .build();
    }
}
