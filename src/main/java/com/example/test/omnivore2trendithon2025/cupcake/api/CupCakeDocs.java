package com.example.test.omnivore2trendithon2025.cupcake.api;

import com.example.test.omnivore2trendithon2025.cupcake.api.dto.request.SaveCupCakeRequest;
import com.example.test.omnivore2trendithon2025.cupcake.api.dto.request.updateAccessRequest;
import com.example.test.omnivore2trendithon2025.cupcake.api.dto.response.*;
import com.example.test.omnivore2trendithon2025.cupcake.domain.AccessRange;
import com.example.test.omnivore2trendithon2025.global.template.RspTemplate;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.time.YearMonth;
import java.util.List;

@Tag(name = "[컵케이크 API]", description = "컵케이크 관련 API")
public interface CupCakeDocs {

    @Operation(summary = "컵케이크 생성", description = "회원 정보를 구분자로 컵케이크를 생성합니다.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "컵케이크 생성 성공",
                            content = @Content(schema = @Schema(implementation = SaveCupCakeResponse.class))),
                    @ApiResponse(responseCode = "400", description = "잘못된 요청"),
                    @ApiResponse(responseCode = "401", description = "인증 실패"),
                    @ApiResponse(responseCode = "500", description = "서버 오류")
            })
    RspTemplate<SaveCupCakeResponse> createCupCake(
            @Parameter(description = "로그인한 유저의 이메일(토큰에서 자동 추출)", hidden = true) String email,
            @Parameter(description = "컵케이크 작성 양식", required = true) SaveCupCakeRequest request);

    @Operation(summary = "컵케이크 조회", description = "컵케이크 고유 id로 컵케이크를 조회합니다.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "컵케이크 조회 성공",
                            content = @Content(schema = @Schema(implementation = CupCakeResponse.class))),
                    @ApiResponse(responseCode = "400", description = "잘못된 요청"),
                    @ApiResponse(responseCode = "401", description = "인증 실패"),
                    @ApiResponse(responseCode = "403", description = "컵케이크 접근 권한 부족"),
                    @ApiResponse(responseCode = "500", description = "서버 오류")
            })
    RspTemplate<CupCakeResponse> findCupCake(
            @Parameter(description = "로그인한 유저의 이메일(토큰에서 자동 추출)", hidden = true) String email,
            @Parameter(description = "컵케이크 고유 id", required = true) Long cupCakeId);

    @Operation(summary = "내 컵케이크 목록 조회", description = "특정한 년도와 달 기준으로 존재하는 컵케이크들을 조회합니다.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "컵케이크 조회 성공",
                            content = @Content(schema = @Schema(implementation = CupCakeYearMonthResponse.class))),
                    @ApiResponse(responseCode = "400", description = "잘못된 요청"),
                    @ApiResponse(responseCode = "401", description = "인증 실패"),
                    @ApiResponse(responseCode = "500", description = "서버 오류")
            })
    RspTemplate<List<CupCakeYearMonthResponse>> findMyCupCakes(
            @Parameter(description = "로그인한 유저의 이메일(토큰에서 자동 추출)", hidden = true) String email,
            @Parameter(description = "조회하려는 년도와 달(yyyy-MM)", required = true)YearMonth yearMonth);

    @Operation(summary = "내 컵케이크 접근 권한 필터 목록 조회", description = "특정한 년도와 달, 접근 권한 기준으로 존재하는 컵케이크들을 조회합니다.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "컵케이크 조회 성공",
                            content = @Content(schema = @Schema(implementation = CupCakeAccessResponse.class))),
                    @ApiResponse(responseCode = "400", description = "잘못된 요청"),
                    @ApiResponse(responseCode = "401", description = "인증 실패"),
                    @ApiResponse(responseCode = "500", description = "서버 오류")
            })
    RspTemplate<List<CupCakeAccessResponse>> findMyCupCakesWithAccessRange(
            @Parameter(description = "로그인한 유저의 이메일(토큰에서 자동 추출)", hidden = true) String email,
            @Parameter(description = "조회하려는 년도와 달(yyyy-MM)", required = true) YearMonth yearMonth,
            @Parameter(description = "접근 권한", required = true) AccessRange range);


    @Operation(summary = "내 팔로워 컵케이크 목록 조회", description = "내 팔로워의 컵케이크를 최신순으로 조회합니다.",
        responses = {
                @ApiResponse(responseCode = "200", description = "컵케이크 조회 성공",
                        content = @Content(schema = @Schema(implementation = FollowCupCakeResponse.class))),
                @ApiResponse(responseCode = "400", description = "잘못된 요청"),
                @ApiResponse(responseCode = "401", description = "인증 실패"),
                @ApiResponse(responseCode = "500", description = "서버 오류")
        })
    RspTemplate<List<FollowCupCakeResponse>> findFollowerCupCakes(
            @Parameter(description = "로그인한 유저의 이메일(토큰에서 자동 추출)", hidden = true) String email,
            @Parameter(description = "페이지 번호", required = true) int page,
            @Parameter(description = "요청할 개수", required = true) int size);

    @Operation(summary = "내 컵케이크 접근 범위 수정", description = "내 컵케이크의 접근 범위를 수정합니다.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "접근 범위 수정 성공"),
                    @ApiResponse(responseCode = "400", description = "잘못된 요청"),
                    @ApiResponse(responseCode = "401", description = "인증 실패"),
                    @ApiResponse(responseCode = "403", description = "수정할 수 있는 권한 없음"),
                    @ApiResponse(responseCode = "500", description = "서버 오류")
            })
    RspTemplate<Void> updateCupCakeAccess(
            @Parameter(description = "로그인한 유저의 이메일(토큰에서 자동 추출)", hidden = true) String email,
            @Parameter(description = "컵케이크 id와 수정하려는 접근 권한") updateAccessRequest request
    );
}

