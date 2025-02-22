package com.example.test.omnivore2trendithon2025.cupcake.api;

import com.example.test.omnivore2trendithon2025.cupcake.api.dto.request.CupCakeYearMonthRequest;
import com.example.test.omnivore2trendithon2025.cupcake.api.dto.request.CupCakeYearMonthWithAccessRequest;
import com.example.test.omnivore2trendithon2025.cupcake.api.dto.request.SaveCupCakeRequest;
import com.example.test.omnivore2trendithon2025.cupcake.api.dto.request.updateAccessRequest;
import com.example.test.omnivore2trendithon2025.cupcake.api.dto.response.CupCakeResponse;
import com.example.test.omnivore2trendithon2025.cupcake.api.dto.response.FollowCupCakeResponse;
import com.example.test.omnivore2trendithon2025.cupcake.api.dto.response.SaveCupCakeResponse;
import com.example.test.omnivore2trendithon2025.cupcake.application.CupCakeService;
import com.example.test.omnivore2trendithon2025.cupcake.domain.AccessRange;
import com.example.test.omnivore2trendithon2025.global.annotation.CurrentUserEmail;
import com.example.test.omnivore2trendithon2025.global.template.RspTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cupcakes")
public class CupCakeController implements CupCakeDocs{

    private final CupCakeService cupCakeService;

    @PostMapping
    public RspTemplate<SaveCupCakeResponse> createCupCake(
            @CurrentUserEmail String email,
            @RequestBody SaveCupCakeRequest request) {

        return new RspTemplate<>(
            HttpStatus.CREATED,
            "컵케이크 생성 완료!",
            cupCakeService.saveCupCake(email, request));
    }

    @GetMapping("/{cupCakeId}")
    public RspTemplate<CupCakeResponse> findCupCake(
            @CurrentUserEmail String email,
            @PathVariable Long cupCakeId) {

        return new RspTemplate<>(
                HttpStatus.OK,
                "컵케이크 조회 완료!",
                cupCakeService.findCupCake(email, cupCakeId)
        );
    }

    @GetMapping("/date")
    public RspTemplate<List<CupCakeResponse>> findMyCupCakes(
            @CurrentUserEmail String email,
            @RequestBody CupCakeYearMonthRequest request
            ){

        return new RspTemplate<>(
                HttpStatus.OK,
                "연도와 월에 맞는 컵케이크들 조회 완료!",
                cupCakeService.findMyCupCakes(email, request.yearMonth())
        );
    }

    @GetMapping("/date/access")
    public RspTemplate<List<CupCakeResponse>> findMyCupCakesWithAccessRange(
            @CurrentUserEmail String email,
            @RequestBody CupCakeYearMonthWithAccessRequest request
            ){

        return new RspTemplate<>(
                HttpStatus.OK,
                "접근 범위, 연도와 월에 맞는 컵케이크들 조회 완료!",
                cupCakeService.findMyCupCakesByFilter(email, request.yearMonth(), request.accessRange())
        );
    }

    @GetMapping("/follow")
    public RspTemplate<List<FollowCupCakeResponse>> findFollowerCupCakes(
            @CurrentUserEmail String email,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size
    ){
        return new RspTemplate<>(
                HttpStatus.OK,
                "내 팔로워 컵케이크 조회 완료!",
                cupCakeService.findFollowersCupCake(email, PageRequest.of(page, size))
        );
    }

    @PatchMapping("/{cupCakeId}")
    public RspTemplate<Void> updateCupCakeAccess(
            @CurrentUserEmail String email,
            @RequestBody updateAccessRequest request
    ){
        cupCakeService.updateCupCakeAccess(email, request);
        return new RspTemplate<>(
                HttpStatus.OK,
                "컵케이크 접근 범위 수정 완료!"
        );
    }
}
