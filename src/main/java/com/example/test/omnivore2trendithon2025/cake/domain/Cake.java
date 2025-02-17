package com.example.test.omnivore2trendithon2025.cake.domain;

import com.example.test.omnivore2trendithon2025.cake.domain.cakecandle.domain.CakeCandle;
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
    @JoinColumn(name = "email")
    private Member member;

    @Enumerated(EnumType.STRING)
    private CakeColor color;

    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(mappedBy = "cake", cascade = CascadeType.ALL)
    private List<CakeCandle> candles = new ArrayList<>();

    private Integer likeCount;

//    @OneToMany(cascade = CascadeType.ALL)
//    private List<Member> likes = new ArrayList<>();

    public static Cake makeNewCake(Member member, CakeColor color) {
        Cake cake = new Cake();
        cake.member = member;
        cake.color = color;
        cake.status = Status.ACTIVE;
        cake.likeCount = 0;
        return cake;
    }

    public void increaseLikeCount() {
        this.likeCount++;
    }

    public void decreaseLikeCount() {
        this.likeCount--;
    }
}
