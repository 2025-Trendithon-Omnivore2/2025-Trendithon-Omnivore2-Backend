package com.example.test.omnivore2trendithon2025.cupcake.domain.repository;

import com.example.test.omnivore2trendithon2025.cupcake.api.dto.response.CupCakeResponse;
import com.example.test.omnivore2trendithon2025.cupcake.api.dto.response.FollowCupCakeResponse;
import com.example.test.omnivore2trendithon2025.cupcake.domain.AccessRange;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

import static com.example.test.omnivore2trendithon2025.cupcake.domain.QCupCake.cupCake;
import static com.example.test.omnivore2trendithon2025.heart.domain.QHeart.heart;

@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CupCakeCustomRepositoryImpl implements CupCakeCustomRepository {
    private EntityManager em;
    private JPAQueryFactory queryFactory;

    @Override
    public List<CupCakeResponse> findByMemberAndYearMonth(String email, int year, int month) {
        return queryFactory
                .select(Projections.constructor(CupCakeResponse.class,
                        cupCake.id,
                        cupCake.emotion,
                        cupCake.content,
                        cupCake.createdAt,
                        cupCake.accessRange,
                        cupCake.likeCount))
                .from(cupCake)
                .where(
                        cupCake.member.email.eq(email),
                        Expressions.numberTemplate(Integer.class, "YEAR({0})", cupCake.createdAt).eq(year),
                        Expressions.numberTemplate(Integer.class, "MONTH({0})", cupCake.createdAt).eq(month)
                )
                .fetch();
    }

    @Override
    public List<CupCakeResponse> findByMemberAndYearMonthAndAccessRange(String email, int year, int month, AccessRange accessRange) {

        return queryFactory
                .select(Projections.constructor(CupCakeResponse.class,
                        cupCake.id,
                        cupCake.emotion,
                        cupCake.content,
                        cupCake.createdAt,
                        cupCake.accessRange,
                        cupCake.likeCount))
                .from(cupCake)
                .where(
                        cupCake.member.email.eq(email),
                        Expressions.numberTemplate(Integer.class, "YEAR({0})", cupCake.createdAt).eq(year),
                        Expressions.numberTemplate(Integer.class, "MONTH({0})", cupCake.createdAt).eq(month),
                        cupCake.accessRange.eq(accessRange)
                )
                .fetch();
    }

    @Override
    public List<FollowCupCakeResponse> findByFollowerIds(Long memberId, List<Long> followerIds) {
        if (followerIds.isEmpty()) {
            return Collections.emptyList();
        }

        return queryFactory
                .select(Projections.constructor(FollowCupCakeResponse.class,
                        cupCake.id,
                        cupCake.emotion,
                        cupCake.createdAt,
                        cupCake.accessRange,
                        cupCake.likeCount,
                        cupCake.member.nickname,
                        ExpressionUtils.as(
                                JPAExpressions
                                        .selectOne()
                                        .from(heart)
                                        .where(
                                                heart.member.id.eq(memberId),
                                                heart.cupCake.member.id.in(followerIds)
                                        )
                                        .exists(),
                                "like"
                        )
                ))
                .from(cupCake)
                .where(cupCake.member.id.in(followerIds))
                .orderBy(cupCake.createdAt.desc())
                .fetch();
    }
}
