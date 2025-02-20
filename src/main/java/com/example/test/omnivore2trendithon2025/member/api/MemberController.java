package com.example.test.omnivore2trendithon2025.member.api;

import com.example.test.omnivore2trendithon2025.global.annotation.CurrentUserEmail;
import com.example.test.omnivore2trendithon2025.global.template.RspTemplate;
import com.example.test.omnivore2trendithon2025.member.mypage.api.request.MyPageUpdateReqDto;
import com.example.test.omnivore2trendithon2025.member.mypage.api.response.MyPageInfoResDto;
import com.example.test.omnivore2trendithon2025.member.mypage.application.MyPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberController implements MemberDocs{

    private final MyPageService myPageService;

    @GetMapping("/my-page")
    public RspTemplate<MyPageInfoResDto> myProfileInfo(@CurrentUserEmail String email) {
        MyPageInfoResDto memberResDto = myPageService.findMyProfileByEmail(email);
        return new RspTemplate<>(HttpStatus.OK, "내 프로필 정보", memberResDto);
    }

    @PatchMapping("/my-page")
    public RspTemplate<MyPageInfoResDto> update(@CurrentUserEmail String email,
                                                @RequestBody MyPageUpdateReqDto myPageUpdateReqDto) {
        return new RspTemplate<>(HttpStatus.OK, "내 프로필 정보 수정", myPageService.update(email, myPageUpdateReqDto));
    }

    //todo 상대방 컵케이크 정보 조회
}
