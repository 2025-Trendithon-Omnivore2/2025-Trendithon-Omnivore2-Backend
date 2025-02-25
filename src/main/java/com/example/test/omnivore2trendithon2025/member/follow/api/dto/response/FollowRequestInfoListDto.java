package com.example.test.omnivore2trendithon2025.member.follow.api.dto.response;

import com.example.test.omnivore2trendithon2025.global.dto.PageInfoResDto;
import java.util.List;
import lombok.Builder;

@Builder
public record FollowRequestInfoListDto(
        List<FollowRequestInfoResDto> followRequestInfoResDto,
        PageInfoResDto pageInfoResDto
) {
    public static FollowRequestInfoListDto of(List<FollowRequestInfoResDto> follows, PageInfoResDto pageInfoResDto) {
        return FollowRequestInfoListDto.builder()
                .followRequestInfoResDto(follows)
                .pageInfoResDto(pageInfoResDto)
                .build();
    }
}
