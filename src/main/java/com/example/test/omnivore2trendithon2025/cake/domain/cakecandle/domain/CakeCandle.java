package com.example.test.omnivore2trendithon2025.cake.domain.cakecandle;

import com.example.test.omnivore2trendithon2025.cake.domain.Cake;
import com.example.test.omnivore2trendithon2025.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class CakeCandle extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cake_id")
    private Cake cake;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String imgUrl;
}
