package com.example.test.omnivore2trendithon2025.member.follow.api.dto.response;

import com.example.test.omnivore2trendithon2025.global.dto.PageInfoResDto;
import java.util.List;
import lombok.Builder;

@Builder
public record FollowInfoListDto(
        List<FollowInfoResDto> followInfoResDto,
        PageInfoResDto pageInfoResDto
) {
    public static FollowInfoListDto of(List<FollowInfoResDto> follows, PageInfoResDto pageInfoResDto) {
        return FollowInfoListDto.builder()
                .followInfoResDto(follows)
                .pageInfoResDto(pageInfoResDto)
                .build();
    }
}
