package com.example.test.omnivore2trendithon2025.cake.domain.cakecandle.api;

import com.example.test.omnivore2trendithon2025.cake.domain.cakecandle.api.dto.request.CakeCandleRequest;
import com.example.test.omnivore2trendithon2025.cake.domain.cakecandle.api.dto.request.UpdateCandleRequest;
import com.example.test.omnivore2trendithon2025.cake.domain.cakecandle.api.dto.response.CakeCandleResponse;
import com.example.test.omnivore2trendithon2025.cake.domain.cakecandle.api.dto.response.SaveCandleResponse;
import com.example.test.omnivore2trendithon2025.cake.domain.cakecandle.application.CakeCandleService;
import com.example.test.omnivore2trendithon2025.global.annotation.CurrentUserEmail;
import com.example.test.omnivore2trendithon2025.global.template.RspTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/candles")
public class CakeCandleController {
    private final CakeCandleService cakeCandleService;

    @PostMapping(value = "/create", consumes = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.MULTIPART_FORM_DATA_VALUE})
    public RspTemplate<SaveCandleResponse> createCandle(
            @CurrentUserEmail String email,
            @RequestPart CakeCandleRequest request,
            @RequestPart MultipartFile image) {
        try{
            return new RspTemplate<>(
                    HttpStatus.CREATED,
                    "케이크에 이미지 생성 완료!",
                    cakeCandleService.saveCandle(email, request, image));

        } catch (IOException e) {
            e.printStackTrace();
            return new RspTemplate<>(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "이미지 업로드 중 에러 발생"
            );
        }
    }

    @GetMapping("/{id}")
    public RspTemplate<CakeCandleResponse> getCandle(@PathVariable("id") Long candleId) {
        CakeCandleResponse response = cakeCandleService.findById(candleId);

        return new RspTemplate<>(
                HttpStatus.OK,
                "사진 조회 완료!",
                response
        );
    }

    @PatchMapping(value = "/update", consumes = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.MULTIPART_FORM_DATA_VALUE})
    public RspTemplate<Void> updateCandle(@CurrentUserEmail String email,
                                          @RequestPart UpdateCandleRequest request,
                                          @RequestPart MultipartFile image) {
        try{
            cakeCandleService.candleUpdate(email, request, image);

            return new RspTemplate<>(
                    HttpStatus.OK,
                    "사진 업데이트 완료!"
            );
        } catch (IOException e) {
            return new RspTemplate<>(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "이미지 업로드 중 에러 발생"
            );
        }
    }
}
