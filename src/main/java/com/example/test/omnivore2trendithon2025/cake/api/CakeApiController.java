package com.example.test.omnivore2trendithon2025.cake.api;

import com.example.test.omnivore2trendithon2025.cake.api.dto.request.SurveyRequest;
import com.example.test.omnivore2trendithon2025.cake.application.CakeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cakes")
public class CakeApiController {

    private final CakeService cakeService;

    @PostMapping
    public ResponseEntity<Long> createCake(@RequestBody
                                               @Valid SurveyRequest request) {
        Long cakeId = cakeService.makeCake(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(cakeId);
    }
}
