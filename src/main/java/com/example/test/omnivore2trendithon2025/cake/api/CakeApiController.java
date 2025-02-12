package com.example.test.omnivore2trendithon2025.cake.api;

import com.example.test.omnivore2trendithon2025.cake.api.dto.request.SurveyRequest;
import com.example.test.omnivore2trendithon2025.cake.api.dto.response.CakeResponse;
import com.example.test.omnivore2trendithon2025.cake.api.dto.response.SurveyResponse;
import com.example.test.omnivore2trendithon2025.cake.application.CakeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cakes")
public class CakeApiController {

    private final CakeService cakeService;

    @PostMapping
    public ResponseEntity<SurveyResponse> createCake(@RequestBody
                                               @Valid SurveyRequest request) {
        Long cakeId = cakeService.makeCake(request);

        SurveyResponse response = SurveyResponse.builder()
                .message("케이크 생성 완료!")
                .cakeId(cakeId)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CakeResponse> findByCakeId(@PathVariable("id") Long cakeId) {
        CakeResponse cakeResponse = cakeService.findByCakeId(cakeId);

        return ResponseEntity.status(HttpStatus.OK)
                .body(cakeResponse);
    }
}
