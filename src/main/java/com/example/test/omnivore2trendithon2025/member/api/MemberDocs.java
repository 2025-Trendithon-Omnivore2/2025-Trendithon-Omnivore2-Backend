package com.example.test.omnivore2trendithon2025.member.api;

import com.example.test.omnivore2trendithon2025.global.annotation.CurrentUserEmail;
import com.example.test.omnivore2trendithon2025.global.template.RspTemplate;
import com.example.test.omnivore2trendithon2025.member.follow.api.dto.request.FollowReqDto;
import com.example.test.omnivore2trendithon2025.member.follow.api.dto.response.FollowInfoListDto;
import com.example.test.omnivore2trendithon2025.member.follow.api.dto.response.FollowResDto;
import com.example.test.omnivore2trendithon2025.member.mypage.api.request.MyPageUpdateReqDto;
import com.example.test.omnivore2trendithon2025.member.mypage.api.response.MyPageInfoResDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "[유저 API]", description = "유저 관련 API")
public interface MemberDocs {

    @Operation(summary = "마이페이지 조회", description = "마이페이지를 조회합니다.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "마이페이지 조회 성공",
                            content = @Content(schema = @Schema(implementation = MyPageInfoResDto.class))),
                    @ApiResponse(responseCode = "400", description = "잘못된 요청"),
                    @ApiResponse(responseCode = "401", description = "인증 실패"),
                    @ApiResponse(responseCode = "500", description = "서버 오류")
            })
    RspTemplate<MyPageInfoResDto> myProfileInfo(
            @Parameter(description = "로그인한 유저의 이메일(토큰에서 자동 추출)", hidden = true) String email);

    @Operation(summary = "마이페이지 수정", description = "닉네임을 수정해서 마이페이지를 수정합니다.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "마이페이지 수정 성공",
                            content = @Content(schema = @Schema(implementation = MyPageInfoResDto.class))),
                    @ApiResponse(responseCode = "400", description = "잘못된 요청"),
                    @ApiResponse(responseCode = "401", description = "인증 실패"),
                    @ApiResponse(responseCode = "500", description = "서버 오류")
            })
    RspTemplate<MyPageInfoResDto> update(
            @Parameter(description = "로그인한 유저의 이메일(토큰에서 자동 추출)", hidden = true) String email,
            @Parameter(description = "변결할 닉네임", required = true) MyPageUpdateReqDto myPageUpdateReqDto);
}