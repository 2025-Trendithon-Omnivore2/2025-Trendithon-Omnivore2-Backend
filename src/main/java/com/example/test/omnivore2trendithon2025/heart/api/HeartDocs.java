package com.example.test.omnivore2trendithon2025.heart.api;

import com.example.test.omnivore2trendithon2025.cake.api.dto.response.SurveyResponse;
import com.example.test.omnivore2trendithon2025.global.annotation.CurrentUserEmail;
import com.example.test.omnivore2trendithon2025.global.template.RspTemplate;
import com.example.test.omnivore2trendithon2025.member.follow.api.dto.request.FollowReqDto;
import com.example.test.omnivore2trendithon2025.member.follow.api.dto.response.FollowInfoListDto;
import com.example.test.omnivore2trendithon2025.member.follow.api.dto.response.FollowResDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PathVariable;

@Tag(name = "[좋아요 API]", description = "좋아요 API")
public interface HeartDocs {

    @Operation(summary = "호출했을 때 좋아요 등록/삭제", description = "좋아요가 안 되어있으면 등록, 좋아요가 되어있으면 삭제 진행.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "좋아요 등록/삭제 성공"),
                    @ApiResponse(responseCode = "400", description = "잘못된 요청"),
                    @ApiResponse(responseCode = "401", description = "인증 실패"),
                    @ApiResponse(responseCode = "500", description = "서버 오류")
            })
    RspTemplate<SurveyResponse> createOrDeleteCakeHeart(
            @Parameter(description = "로그인한 유저의 이메일(토큰에서 자동 추출)", hidden = true) String email,
            @Parameter(description = "cake 고유 Id", required = true) Long cakeId);
}