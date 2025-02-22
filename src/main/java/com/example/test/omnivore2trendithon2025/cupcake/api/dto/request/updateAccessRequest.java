package com.example.test.omnivore2trendithon2025.cupcake.api.dto.request;

import com.example.test.omnivore2trendithon2025.cupcake.domain.AccessRange;

public record updateAccessRequest(
        Long cupCakeId,
        AccessRange range
) {
}
