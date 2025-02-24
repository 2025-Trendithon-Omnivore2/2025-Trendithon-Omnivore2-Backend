package com.example.test.omnivore2trendithon2025.cake.domain.cakecandle.api;

import com.example.test.omnivore2trendithon2025.cake.domain.cakecandle.api.dto.request.CakeCandleRequest;
import com.example.test.omnivore2trendithon2025.cake.domain.cakecandle.api.dto.request.UpdateCandleRequest;
import com.example.test.omnivore2trendithon2025.cake.domain.cakecandle.api.dto.response.CakeCandleResponse;
import com.example.test.omnivore2trendithon2025.cake.domain.cakecandle.api.dto.response.SaveCandleResponse;
import com.example.test.omnivore2trendithon2025.global.template.RspTemplate;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "[케이크 이미지 API]", description = "케이크 이미지 관련 API")
public interface CakeCandleDocs {

    @Operation(summary = "케이크에 이미지 생성", description = "케이크에 이미지를 생성합니다.",
    responses = {
            @ApiResponse(responseCode = "201", description = "이미지 생성 성공",
            content = @Content(schema = @Schema(implementation = SaveCandleResponse.class))),
            @ApiResponse(responseCode = "400", description = "잘못된 요청"),
            @ApiResponse(responseCode = "401", description = "인증 실패"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    RspTemplate<SaveCandleResponse> createCandle(
            @Parameter(description = "로그인한 유저의 이메일(토큰에서 자동 추출)", hidden = true) String email,
            @Parameter(description = "작성한 내용 및 인덱스", required = true) CakeCandleRequest request,
            @Parameter(description = "이미지 파일", required = true) MultipartFile image);

    @Operation(summary = "케이크 이미지 id 기반 조회", description = "케이크 이미지를 고유 id를 통해 조회합니다.",
    responses = {
            @ApiResponse(responseCode = "200", description = "이미지 조회 성공",
                    content = @Content(schema = @Schema(implementation = CakeCandleResponse.class))),
            @ApiResponse(responseCode = "400", description = "잘못된 요청"),
            @ApiResponse(responseCode = "401", description = "인증 실패"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    RspTemplate<CakeCandleResponse> getCandle(
            @Parameter(description = "케이크 이미지 고유 id", required = true) Long candleId);


    @Operation(summary = "케이크 이미지 업데이트", description = "케이크 이미지를 업데이트 합니다.",
    responses = {
            @ApiResponse(responseCode = "200", description = "업데이트 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청"),
            @ApiResponse(responseCode = "401", description = "인증 실패"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    RspTemplate<Void> updateCandle(@Parameter(description = "로그인한 유저의 이메일(토큰에서 자동 추출)", hidden = true) String email,
                                   @Parameter(description = "이미지 고유 id와 작성 내용", required = true) UpdateCandleRequest request,
                                   @Parameter(description = "이미지 파일") MultipartFile image);
}
