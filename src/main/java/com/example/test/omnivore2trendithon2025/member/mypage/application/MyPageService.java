package com.example.test.omnivore2trendithon2025.member.mypage.application;

import com.example.test.omnivore2trendithon2025.member.domain.Member;
import com.example.test.omnivore2trendithon2025.member.domain.repository.MemberRepository;
import com.example.test.omnivore2trendithon2025.member.exception.MemberNotFoundException;
import com.example.test.omnivore2trendithon2025.member.follow.api.dto.response.MyFollowsResDto;
import com.example.test.omnivore2trendithon2025.member.follow.domain.repository.FollowRepository;
import com.example.test.omnivore2trendithon2025.member.mypage.api.request.MyPageUpdateReqDto;
import com.example.test.omnivore2trendithon2025.member.mypage.api.response.MyPageInfoResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MyPageService {

    private final MemberRepository memberRepository;
    private final FollowRepository followRepository;

    public MyPageInfoResDto findMyProfileByEmail(String email) {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(MemberNotFoundException::new);

        MyFollowsResDto followCountDto = followRepository.findMyFollowsCount(member.getId());

        return MyPageInfoResDto.From(member,followCountDto.myFollowsCount());
    }

    @Transactional
    public MyPageInfoResDto update(String email, MyPageUpdateReqDto myPageUpdateReqDto) {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(MemberNotFoundException::new);

        MyFollowsResDto followCountDto = followRepository.findMyFollowsCount(member.getId());

        member.update(myPageUpdateReqDto.nickname());

        return MyPageInfoResDto.From(member, followCountDto.myFollowsCount());
    }
}
