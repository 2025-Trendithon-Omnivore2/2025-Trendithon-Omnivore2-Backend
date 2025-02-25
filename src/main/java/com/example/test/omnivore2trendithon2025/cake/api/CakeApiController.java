package com.example.test.omnivore2trendithon2025.cake.api;

import com.example.test.omnivore2trendithon2025.cake.api.dto.request.SurveyRequest;
import com.example.test.omnivore2trendithon2025.cake.api.dto.response.GuestCakeResponse;
import com.example.test.omnivore2trendithon2025.cake.api.dto.response.MyCakeResponse;
import com.example.test.omnivore2trendithon2025.cake.api.dto.response.OtherCakeResponse;
import com.example.test.omnivore2trendithon2025.cake.api.dto.response.SurveyResponse;
import com.example.test.omnivore2trendithon2025.cake.application.CakeService;
import com.example.test.omnivore2trendithon2025.global.annotation.CurrentUserEmail;
import com.example.test.omnivore2trendithon2025.global.template.RspTemplate;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cakes")
public class CakeApiController implements CakeDocs{

    private final CakeService cakeService;

    @PostMapping
    public RspTemplate<SurveyResponse> createCake(
            @CurrentUserEmail String email,
            @RequestBody @Valid SurveyRequest request) {

        Long cakeId = cakeService.makeCake(email, request);

        SurveyResponse response = SurveyResponse.builder()
                .cakeId(cakeId)
                .build();

        return new RspTemplate<>(
                HttpStatus.CREATED,
                "케이크 생성 완료!",
                response);
    }

    @GetMapping("/cake/{cakeId}")
    public RspTemplate<OtherCakeResponse> findByCakeId(
            @CurrentUserEmail String email,
            @PathVariable Long cakeId) {
        OtherCakeResponse cakeResponse = cakeService.findByCakeId(cakeId, email);

        return new RspTemplate<>(
                HttpStatus.OK,
                "케이크 조회 완료!",
                cakeResponse);
    }


    @GetMapping
    public RspTemplate<MyCakeResponse> findByMemberEmail(@CurrentUserEmail String email) {
        MyCakeResponse cakeResponse = cakeService.findByMemberEmail(email);

        return new RspTemplate<>(
                HttpStatus.OK,
                "케이크 조회 완료!",
                cakeResponse
        );
    }

    @GetMapping("/member/{memberId}")
    public RspTemplate<OtherCakeResponse> findByMemberId(
            @CurrentUserEmail String email,
            @PathVariable Long memberId) {
        return new RspTemplate<>(
                HttpStatus.OK,
                "케이크 조회 완료!",
                cakeService.findByMemberId(email, memberId)
        );
    }

    @GetMapping("/follow")
    public RspTemplate<List<OtherCakeResponse>> findFollowerCakes(
            @CurrentUserEmail String email,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size
    ) {
        return new RspTemplate<>(HttpStatus.OK,
                "팔로워 케이크 조회 완료!",
                cakeService.findFollowerCakes(email, PageRequest.of(page, size)));
    }

    @GetMapping("/{memberId}")
    public RspTemplate<GuestCakeResponse> shareCake(@PathVariable Long memberId) {
        return new RspTemplate<>(
                HttpStatus.OK,
                "공유용 링크로 케이크 조회 완료!",
                cakeService.findShareCake(memberId)
        );
    }


}
