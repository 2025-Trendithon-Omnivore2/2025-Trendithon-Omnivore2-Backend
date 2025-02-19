package com.example.test.omnivore2trendithon2025.cupcake.domain.repository;

import com.example.test.omnivore2trendithon2025.cupcake.api.dto.response.CupCakeResponse;
import com.example.test.omnivore2trendithon2025.cupcake.domain.AccessRange;

import java.util.List;

public interface CupCakeCustomRepository {
    List<CupCakeResponse> findByMemberAndYearMonth(String email, int year, int month);

    List<CupCakeResponse> findByMemberAndYearMonthAndAccessRange(String email, int year, int monthValue, AccessRange accessRange);
}
