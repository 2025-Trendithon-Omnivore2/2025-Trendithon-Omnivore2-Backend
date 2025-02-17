package com.example.test.omnivore2trendithon2025.heart.domain.repository;

import com.example.test.omnivore2trendithon2025.member.domain.Member;

public interface HeartCustomRepository {
    void createOrDeleteHeart(Member member, Long cakeId);
}
