package com.example.test.omnivore2trendithon2025.heart.api;

import com.example.test.omnivore2trendithon2025.cake.api.dto.response.SurveyResponse;
import com.example.test.omnivore2trendithon2025.global.annotation.CurrentUserEmail;
import com.example.test.omnivore2trendithon2025.global.template.RspTemplate;
import com.example.test.omnivore2trendithon2025.heart.application.HeartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/hearts")
public class HeartController implements HeartDocs {

    private final HeartService heartService;

    @PostMapping("/{cakeId}")
    public RspTemplate<SurveyResponse> createOrDeleteHeart(
            @CurrentUserEmail String email,
            @PathVariable Long cakeId) {

        heartService.createOrDeleteHeart(email, cakeId);

        return new RspTemplate<>(HttpStatus.OK, "좋아요 등록/삭제 성공");
    }
}
