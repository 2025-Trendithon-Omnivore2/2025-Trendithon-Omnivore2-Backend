package com.example.test.omnivore2trendithon2025.heart.application;

import com.example.test.omnivore2trendithon2025.heart.domain.repository.HeartRepository;
import com.example.test.omnivore2trendithon2025.member.domain.Member;
import com.example.test.omnivore2trendithon2025.member.domain.repository.MemberRepository;
import com.example.test.omnivore2trendithon2025.member.exception.MemberNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class HeartService {

    private final HeartRepository heartRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public void createOrDeleteCakeHeart(String email, Long cakeId) {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(MemberNotFoundException::new);

        heartRepository.createOrDeleteCakeHeart(member, cakeId);
    }

    @Transactional
    public void createOrDeleteCupCakeHeart(String email, Long cupCakeId) {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(MemberNotFoundException::new);

        heartRepository.createOrDeleteCupCakeHeart(member, cupCakeId);
    }

}
