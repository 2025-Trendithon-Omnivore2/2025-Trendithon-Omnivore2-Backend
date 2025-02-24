package com.example.test.omnivore2trendithon2025.cupcake.api.dto.response;

import com.example.test.omnivore2trendithon2025.cupcake.domain.AccessRange;
import lombok.Builder;

@Builder
public record AccessRangeResponse(
        AccessRange access
) {
}
