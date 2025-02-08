package com.example.test.omnivore2trendithon2025.auth.api.dto.response;


import com.example.test.omnivore2trendithon2025.member.domain.Member;
import lombok.Builder;

@Builder
public record MemberLoginResDto(
        Member findMember
) {
    public static MemberLoginResDto from(Member member) {
        return MemberLoginResDto.builder()
                .findMember(member)
                .build();
    }
}
