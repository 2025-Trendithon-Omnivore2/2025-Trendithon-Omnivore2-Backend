package com.example.test.omnivore2trendithon2025.member.follow.api.dto.response;

import com.example.test.omnivore2trendithon2025.global.dto.PageInfoResDto;
import java.util.List;
import lombok.Builder;

@Builder
public record MemberInfoForFollowListDto(
        List<MemberInfoForFollowResDto> memberInfoForFollowResDtos,
        PageInfoResDto pageInfoResDto
) {
    public static MemberInfoForFollowListDto of(List<MemberInfoForFollowResDto> infoResDtos,
                                                PageInfoResDto pageInfoResDto) {
        return MemberInfoForFollowListDto.builder()
                .memberInfoForFollowResDtos(infoResDtos)
                .pageInfoResDto(pageInfoResDto)
                .build();
    }
}
