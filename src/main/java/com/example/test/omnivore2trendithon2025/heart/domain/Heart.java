package com.example.test.omnivore2trendithon2025.heart.domain;

import com.example.test.omnivore2trendithon2025.global.entity.BaseEntity;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Heart extends BaseEntity {
    private Long HeartCount;
}
