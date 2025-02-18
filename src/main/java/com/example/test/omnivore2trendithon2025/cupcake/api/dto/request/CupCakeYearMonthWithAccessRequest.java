package com.example.test.omnivore2trendithon2025.cupcake.api.dto.request;

import com.example.test.omnivore2trendithon2025.cupcake.domain.AccessRange;

import java.time.YearMonth;

public record CupCakeYearMonthWithAccessRequest(
        YearMonth yearMonth,
        AccessRange accessRange
) {
}
