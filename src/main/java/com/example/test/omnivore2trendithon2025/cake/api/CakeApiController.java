package com.example.test.omnivore2trendithon2025.cake.api;

import com.example.test.omnivore2trendithon2025.cake.api.dto.request.SurveyRequest;
import com.example.test.omnivore2trendithon2025.cake.api.dto.response.CakeResponse;
import com.example.test.omnivore2trendithon2025.cake.api.dto.response.SurveyResponse;
import com.example.test.omnivore2trendithon2025.cake.application.CakeService;
import com.example.test.omnivore2trendithon2025.global.template.RspTemplate;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cakes")
public class CakeApiController {

    private final CakeService cakeService;

    @PostMapping
    public RspTemplate<SurveyResponse> createCake(@RequestBody
                                               @Valid SurveyRequest request) {
        Long cakeId = cakeService.makeCake(request);

        SurveyResponse response = SurveyResponse.builder()
                .cakeId(cakeId)
                .build();

        return new RspTemplate<>(
                HttpStatus.CREATED,
                "케이크 생성 완료!",
                response);
    }

    @GetMapping("/{id}")
    public RspTemplate<CakeResponse> findByCakeId(@PathVariable("id") Long cakeId) {
        CakeResponse cakeResponse = cakeService.findByCakeId(cakeId);

        return new RspTemplate<>(
                HttpStatus.OK,
                "케이크 조회 완료!",
                cakeResponse);
    }
}
