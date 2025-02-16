package com.example.test.omnivore2trendithon2025.member.follow.api;

import com.example.test.omnivore2trendithon2025.auth.api.dto.request.RefreshTokenReqDto;
import com.example.test.omnivore2trendithon2025.auth.api.dto.request.TokenReqDto;
import com.example.test.omnivore2trendithon2025.auth.api.dto.response.IdTokenResDto;
import com.example.test.omnivore2trendithon2025.global.annotation.CurrentUserEmail;
import com.example.test.omnivore2trendithon2025.global.jwt.api.dto.TokenDto;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "[친구 API]", description = "친구 관련 API")
public interface FollowDocs {

    @Operation(summary = "친구 추가 요청", description = "친구 추가 요청을 합니다.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "친구 추가 요청 성공",
                            content = @Content(schema = @Schema(implementation = FollowResDto.class))),
                    @ApiResponse(responseCode = "400", description = "잘못된 요청"),
                    @ApiResponse(responseCode = "401", description = "인증 실패"),
                    @ApiResponse(responseCode = "500", description = "서버 오류")
            })
    RspTemplate<FollowResDto> save(
            @Parameter(description = "로그인한 유저의 이메일(토큰에서 자동 추출)", hidden = true) String email,
            @Parameter(description = "친구 요청할 유저의 이메일", required = true) FollowReqDto followReqDto);

    @Operation(summary = "친구 추가 수락", description = "친구 추가 요청을 수락합니다.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "친구 수락 성공"),
                    @ApiResponse(responseCode = "400", description = "잘못된 요청"),
                    @ApiResponse(responseCode = "401", description = "인증 실패"),
                    @ApiResponse(responseCode = "500", description = "서버 오류")
            })
    RspTemplate<Void> accept(
            @Parameter(description = "follow 고유 ID", required = true) Long followId);

    @Operation(summary = "내 친구 목록 조회", description = "내 친구 목록을 조회합니다.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "내 친구 목록 조회 성공",
                            content = @Content(schema = @Schema(implementation = FollowInfoListDto.class))),
                    @ApiResponse(responseCode = "400", description = "잘못된 요청"),
                    @ApiResponse(responseCode = "401", description = "인증 실패"),
                    @ApiResponse(responseCode = "500", description = "서버 오류")
            })
    RspTemplate<FollowInfoListDto> findFollowList(
            @Parameter(description = "로그인한 유저의 이메일(토큰에서 자동 추출)", hidden = true) String email,
            @Parameter(description = "페이지 번호", required = true) int page,
            @Parameter(description = "요청할 개수", required = true) int size
    );

    @Operation(summary = "나에게 친구 신청한 유저 목록 조회", description = "나에게 친구 신청한 유저 목록을 조회합니다.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "나에게 친구 신청한 유저 목록 조회 성공",
                            content = @Content(schema = @Schema(implementation = FollowInfoListDto.class))),
                    @ApiResponse(responseCode = "400", description = "잘못된 요청"),
                    @ApiResponse(responseCode = "401", description = "인증 실패"),
                    @ApiResponse(responseCode = "500", description = "서버 오류")
            })
    RspTemplate<FollowInfoListDto> findMyFollows(
            @Parameter(description = "로그인한 유저의 이메일(토큰에서 자동 추출)", hidden = true) String email,
            @Parameter(description = "페이지 번호", required = true) int page,
            @Parameter(description = "요청할 개수", required = true) int size);

    @Operation(summary = "친구 삭제", description = "친구를 삭제합니다.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "친구 삭제 성공"),
                    @ApiResponse(responseCode = "400", description = "잘못된 요청"),
                    @ApiResponse(responseCode = "401", description = "인증 실패"),
                    @ApiResponse(responseCode = "500", description = "서버 오류")
            })
    RspTemplate<Void> delete(
            @Parameter(description = "로그인한 유저의 이메일(토큰에서 자동 추출)", hidden = true) String email,
            @Parameter(description = "member 고유 Id", required = true) Long memberId);
}