package com.example.test.omnivore2trendithon2025.cake.api;

import com.example.test.omnivore2trendithon2025.cake.api.dto.request.SurveyRequest;
import com.example.test.omnivore2trendithon2025.cake.api.dto.response.MyCakeResponse;
import com.example.test.omnivore2trendithon2025.cake.api.dto.response.OtherCakeResponse;
import com.example.test.omnivore2trendithon2025.cake.api.dto.response.SurveyResponse;
import com.example.test.omnivore2trendithon2025.global.annotation.CurrentUserEmail;
import com.example.test.omnivore2trendithon2025.global.template.RspTemplate;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Tag(name = "[케이크 API]", description = "케이크 관련 API")
public interface CakeDocs {

    @Operation(summary = "케이크 생성", description = "회원 정보를 구분자로 케이크를 생성합니다.",
    responses = {
            @ApiResponse(responseCode = "201", description = "케이크 생성 성공",
            content = @Content(schema = @Schema(implementation = SurveyResponse.class))),
            @ApiResponse(responseCode = "400", description = "잘못된 요청"),
            @ApiResponse(responseCode = "401", description = "인증 실패"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    RspTemplate<SurveyResponse> createCake(
            @Parameter(description = "로그인한 유저의 이메일(토큰에서 자동 추출)", hidden = true) String email,
            @Parameter(description = "케이크 색깔(예시: STRAWBERRY)", required = true) SurveyRequest request);


    @Operation(summary = "케이크 id 기반 조회", description = "케이크 정보를 고유 id로 조회합니다.",
    responses = {
            @ApiResponse(responseCode = "200", description = "케이크 조회 성공",
            content = @Content(schema = @Schema(implementation = OtherCakeResponse.class))),
            @ApiResponse(responseCode = "400", description = "잘못된 요청"),
            @ApiResponse(responseCode = "401", description = "인증 실패"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    RspTemplate<OtherCakeResponse> findByCakeId(
            @Parameter(description = "로그인한 유저의 이메일(토큰에서 자동 추출)", hidden = true) String email,
            @Parameter(description = "케이크 고유 id", required = true) Long cakeId);


    @Operation(summary = "케이크 이메일 기반 조회", description = "케이크 정보를 토큰을 통한 이메일로 조회합니다.",
    responses = {
            @ApiResponse(responseCode = "200", description = "케이크 조회 성공",
            content = @Content(schema = @Schema(implementation = MyCakeResponse.class))),
            @ApiResponse(responseCode = "400", description = "잘못된 요청"),
            @ApiResponse(responseCode = "401", description = "인증 실패"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    RspTemplate<MyCakeResponse> findByMemberEmail(
            @Parameter(description = "로그인한 유저의 이메일(토큰에서 자동 추출)", hidden = true) String email);


    @Operation(summary = "케이크 사용자 id 기반 조회", description = "케이크 정보를 사용자 고유 id 기반으로 조회합니다.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "케이크 조회 성공",
                            content = @Content(schema = @Schema(implementation = OtherCakeResponse.class))),
                    @ApiResponse(responseCode = "400", description = "잘못된 요청"),
                    @ApiResponse(responseCode = "401", description = "인증 실패"),
                    @ApiResponse(responseCode = "500", description = "서버 오류")
            })
    RspTemplate<OtherCakeResponse> findByMemberId(
            @Parameter(description = "로그인한 유저의 이메일(토큰에서 자동 추출)", hidden = true) String email,
            @Parameter(description = "사용자 고유 ID", required = true) Long memberId);


    @Operation(summary = "팔로워 케이크 조회", description = "사용자의 팔로워 케이크 목록을 조회합니다.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "케이크 조회 성공",
                            content = @Content(schema = @Schema(implementation = OtherCakeResponse.class))),
                    @ApiResponse(responseCode = "400", description = "잘못된 요청"),
                    @ApiResponse(responseCode = "401", description = "인증 실패"),
                    @ApiResponse(responseCode = "500", description = "서버 오류")
            })
    RspTemplate<List<OtherCakeResponse>> findFollowerCakes(
            @Parameter(description = "로그인한 유저의 이메일(토큰에서 자동 추출)", hidden = true) String email,
            @Parameter(description = "페이지 번호", required = true) int page,
            @Parameter(description = "요청할 개수", required = true) int size);
}
