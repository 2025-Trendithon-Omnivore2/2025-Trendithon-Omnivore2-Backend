package com.example.test.omnivore2trendithon2025.cake.domain.repository;

import com.example.test.omnivore2trendithon2025.cake.api.dto.response.OtherCakeResponse;
import com.example.test.omnivore2trendithon2025.cake.domain.Cake;
import com.example.test.omnivore2trendithon2025.member.domain.Member;

import java.util.List;
import java.util.Optional;

public interface CakeCustomRepository {
    Optional<Cake> findByMemberEmail(String email);

    Optional<OtherCakeResponse> findByMemberId(Member member, Long memberId);

    List<OtherCakeResponse> findFollowerCakes(Member member, List<Long> followerIds);
}
