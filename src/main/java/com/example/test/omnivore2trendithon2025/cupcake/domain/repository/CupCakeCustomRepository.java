package com.example.test.omnivore2trendithon2025.cupcake.domain.repository;

import com.example.test.omnivore2trendithon2025.cupcake.api.dto.response.CupCakeAccessResponse;
import com.example.test.omnivore2trendithon2025.cupcake.api.dto.response.CupCakeYearMonthResponse;
import com.example.test.omnivore2trendithon2025.cupcake.api.dto.response.FollowCupCakeResponse;
import com.example.test.omnivore2trendithon2025.cupcake.domain.AccessRange;
import com.example.test.omnivore2trendithon2025.cupcake.domain.CupCake;

import java.time.LocalDate;
import java.util.List;

public interface CupCakeCustomRepository {
    List<CupCakeYearMonthResponse> findByMemberAndYearMonth(String email, int year, int month);

    List<CupCakeAccessResponse> findByMemberAndYearMonthAndAccessRange(String email, int year, int monthValue, AccessRange accessRange);

    List<FollowCupCakeResponse> findByFollowerIds(Long memberId, List<Long> followerIds);

    List<CupCake> findByEmail(String email);

    boolean isExistByLocalDate(LocalDate date);
}
