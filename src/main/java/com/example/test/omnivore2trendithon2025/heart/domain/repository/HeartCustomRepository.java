package com.example.test.omnivore2trendithon2025.heart.domain.repository;

import com.example.test.omnivore2trendithon2025.member.domain.Member;

public interface HeartCustomRepository {
    void createOrDeleteCakeHeart(Member member, Long cakeId);

    void createOrDeleteCupCakeHeart(Member member, Long cupCakeId);
}
