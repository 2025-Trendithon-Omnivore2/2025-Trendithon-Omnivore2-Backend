package com.example.test.omnivore2trendithon2025.cake.domain;

import com.example.test.omnivore2trendithon2025.global.entity.BaseEntity;
import com.example.test.omnivore2trendithon2025.global.entity.Status;
import com.example.test.omnivore2trendithon2025.member.domain.Member;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class Cake extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @NotEmpty
    @Enumerated(EnumType.STRING)
    private CakeColor color;

    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(mappedBy = "cake", cascade = CascadeType.ALL)
    private List<CakeCandle> candles = new ArrayList<>();

    private int likes_count;

//    @OneToMany(cascade = CascadeType.ALL)
//    private List<CakeLike> likes = new ArrayList<>();

    public static Cake makeNewCake(Member member, CakeColor color) {
        Cake cake = new Cake();
        cake.member = member;
        cake.color = color;
        cake.status = Status.ACTIVE;
        return cake;
    }
}
