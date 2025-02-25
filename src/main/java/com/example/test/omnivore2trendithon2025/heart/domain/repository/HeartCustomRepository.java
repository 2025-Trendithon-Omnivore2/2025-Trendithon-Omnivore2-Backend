package com.example.test.omnivore2trendithon2025.heart.domain.repository;

import com.example.test.omnivore2trendithon2025.cake.domain.Cake;
import com.example.test.omnivore2trendithon2025.member.domain.Member;

import java.util.List;

public interface HeartCustomRepository {
    void createOrDeleteCakeHeart(Member member, Long cakeId);

    void createOrDeleteCupCakeHeart(Member member, Long cupCakeId);

    boolean existsByMemberAndCakeId(Member member, Long cakeId);

    boolean existsByMemberAndCupCakeId(Member member, Long cupCakeId);

    List<Boolean> findHeartsForCakes(List<Cake> cakes, Member member);
}
