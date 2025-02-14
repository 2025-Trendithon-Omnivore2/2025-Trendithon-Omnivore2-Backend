package com.example.test.omnivore2trendithon2025.member.follow.api;

import com.example.test.omnivore2trendithon2025.global.annotation.CurrentUserEmail;
import com.example.test.omnivore2trendithon2025.global.template.RspTemplate;
import com.example.test.omnivore2trendithon2025.member.follow.api.dto.request.FollowReqDto;
import com.example.test.omnivore2trendithon2025.member.follow.api.dto.response.FollowInfoListDto;
import com.example.test.omnivore2trendithon2025.member.follow.api.dto.response.FollowResDto;
import com.example.test.omnivore2trendithon2025.member.follow.api.dto.response.MyFollowsResDto;
import com.example.test.omnivore2trendithon2025.member.follow.application.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/member/follow")
public class FollowController {

    private final FollowService followService;

    @PostMapping
    public RspTemplate<FollowResDto> save(@CurrentUserEmail String email,
                                          @RequestBody FollowReqDto followReqDto) {
        return new RspTemplate<>(HttpStatus.OK,
                "친구 추가 요청",
                followService.save(email, followReqDto));
    }

    @PostMapping("/accept/{followId}")
    public RspTemplate<Void> accept(@PathVariable Long followId) {
        followService.accept(followId);

        return new RspTemplate<>(HttpStatus.OK,
                "친구 추가 수락");
    }

    @GetMapping
    public RspTemplate<FollowInfoListDto> findFollowList(@CurrentUserEmail String email,
                                                         @RequestParam(name = "page", defaultValue = "0") int page,
                                                         @RequestParam(name = "size", defaultValue = "10") int size) {
        return new RspTemplate<>(HttpStatus.OK,
                "내 친구 목록 조회",
                followService.findFollowList(email, PageRequest.of(page, size)));
    }

    @GetMapping("/requests")
    public RspTemplate<FollowInfoListDto> findMyFollows(@CurrentUserEmail String email,
                                                      @RequestParam(name = "page", defaultValue = "0") int page,
                                                      @RequestParam(name = "size", defaultValue = "10") int size) {
        return new RspTemplate<>(HttpStatus.OK,
                "친구 요청 목록 조회",
                followService.getMemberFollowRequestList(email, PageRequest.of(page, size)));
    }

    @DeleteMapping("/{memberId}")
    public RspTemplate<Void> delete(@CurrentUserEmail String email, @PathVariable Long memberId) {
        followService.delete(email, memberId);

        return new RspTemplate<>(HttpStatus.OK,
                "친구 삭제");
    }
}