package com.example.test.omnivore2trendithon2025.cupcake.domain;

import com.example.test.omnivore2trendithon2025.global.entity.BaseEntity;
import com.example.test.omnivore2trendithon2025.member.domain.Member;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class CupCake extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private Integer likeCount;

    @Column(nullable = false)
    private String content;

    @Enumerated(EnumType.STRING)
    private AccessRange accessRange;

    @Enumerated(EnumType.STRING)
    private Emotion emotion;

    public void increaseLikeCount() {
        this.likeCount++;
    }

    public void decreaseLikeCount() {
        this.likeCount--;
    }
}
