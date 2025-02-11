package com.example.test.omnivore2trendithon2025.cake.domain;

import com.example.test.omnivore2trendithon2025.global.entity.BaseEntity;
import com.example.test.omnivore2trendithon2025.global.entity.Status;
import com.example.test.omnivore2trendithon2025.member.domain.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class Cake extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Enumerated(EnumType.STRING)
    private cakeColor color;

    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(mappedBy = "cake", cascade = CascadeType.ALL)
    private List<CakeCandle> candles = new ArrayList<>();

//    @OneToMany(cascade = CascadeType.ALL)
//    private List<CakeLike> likes = new ArrayList<>();
}
