package com.example.test.omnivore2trendithon2025.cupcake.domain;

import com.example.test.omnivore2trendithon2025.global.entity.BaseEntity;
import com.example.test.omnivore2trendithon2025.member.domain.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
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

    public static CupCake createCupCake(Member member, Emotion emotion, String content, AccessRange accessRange) {
        CupCake cupCake = new CupCake();
        cupCake.member = member;
        cupCake.emotion = emotion;
        cupCake.content = content;
        cupCake.accessRange = accessRange;
        cupCake.likeCount = 0;

        return cupCake;
    }
}
