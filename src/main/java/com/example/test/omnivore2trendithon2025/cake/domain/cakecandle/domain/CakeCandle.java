package com.example.test.omnivore2trendithon2025.cake.domain.cakecandle.domain;

import com.example.test.omnivore2trendithon2025.cake.domain.Cake;
import com.example.test.omnivore2trendithon2025.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "cake_candle")
public class CakeCandle extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cake_id")
    private Cake cake;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String imgUrl;

    public static CakeCandle createCandle(Cake cake, String content, String imgUrl) {
        CakeCandle cakeCandle = new CakeCandle();
        cakeCandle.cake = cake;
        cakeCandle.content = content;
        cakeCandle.imgUrl = imgUrl;

        return cakeCandle;
    }
}
