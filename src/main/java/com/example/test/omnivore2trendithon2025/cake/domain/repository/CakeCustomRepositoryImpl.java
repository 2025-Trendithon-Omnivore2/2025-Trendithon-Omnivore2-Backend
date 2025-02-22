package com.example.test.omnivore2trendithon2025.cake.domain.repository;

import com.example.test.omnivore2trendithon2025.cake.api.dto.response.OtherCakeResponse;
import com.example.test.omnivore2trendithon2025.cake.domain.Cake;
import com.example.test.omnivore2trendithon2025.cake.domain.QCake;
import com.example.test.omnivore2trendithon2025.cupcake.api.dto.response.FollowCupCakeResponse;
import com.example.test.omnivore2trendithon2025.member.domain.Member;
import com.querydsl.core.QueryFactory;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.example.test.omnivore2trendithon2025.cake.domain.QCake.cake;
import static com.example.test.omnivore2trendithon2025.cupcake.domain.QCupCake.cupCake;
import static com.example.test.omnivore2trendithon2025.heart.domain.QHeart.heart;
import static com.example.test.omnivore2trendithon2025.member.domain.QMember.member;

@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CakeCustomRepositoryImpl implements CakeCustomRepository {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<Cake> findByMemberEmail(String email) {

        return Optional.ofNullable(queryFactory
                        .selectFrom(QCake.cake)
                        .join(cake.member, member)
                        .where(member.email.eq(email))
                        .fetchOne());
    }

    @Override
    public Optional<Cake> findByMemberId(Long memberId) {

        return Optional.ofNullable(queryFactory
                .selectFrom(cake)
                .join(cake.member, member)
                .where(member.id.eq(memberId))
                .fetchOne());
    }

    @Override
    public List<OtherCakeResponse> findFollowerCakes(Member member, List<Long> followerIds) {
        if (followerIds.isEmpty()) {
            return Collections.emptyList();
        }

        return queryFactory
                .select(Projections.constructor(OtherCakeResponse.class,
                        cake.id,
                        cake.color,
                        cake.likeCount,
                        cake.member.nickname,
                        cake.candles,
                        ExpressionUtils.as(
                                JPAExpressions
                                        .selectOne()
                                        .from(heart)
                                        .where(
                                                heart.member.eq(member),
                                                heart.cake.member.id.in(followerIds)
                                        )
                                        .exists(),
                                "like"
                        )
                ))
                .from(cake)
                .where(cake.member.id.in(followerIds))
                .fetch();
    }
}
