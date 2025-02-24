package com.example.test.omnivore2trendithon2025.cupcake.domain.repository;

import com.example.test.omnivore2trendithon2025.cupcake.api.dto.response.CupCakeAccessResponse;
import com.example.test.omnivore2trendithon2025.cupcake.api.dto.response.CupCakeYearMonthResponse;
import com.example.test.omnivore2trendithon2025.cupcake.api.dto.response.FollowCupCakeResponse;
import com.example.test.omnivore2trendithon2025.cupcake.domain.AccessRange;
import com.example.test.omnivore2trendithon2025.cupcake.domain.CupCake;
import com.example.test.omnivore2trendithon2025.member.domain.Member;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static com.example.test.omnivore2trendithon2025.cupcake.domain.QCupCake.cupCake;
import static com.example.test.omnivore2trendithon2025.heart.domain.QHeart.heart;
import static com.example.test.omnivore2trendithon2025.member.domain.QMember.member;

@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CupCakeCustomRepositoryImpl implements CupCakeCustomRepository {
    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    @Override
    public List<CupCakeYearMonthResponse> findByMemberAndYearMonth(String email, int year, int month) {
        return queryFactory
                .select(Projections.constructor(CupCakeYearMonthResponse.class,
                        cupCake.id,
                        cupCake.emotion,
                        cupCake.createdAt,
                        cupCake.content,
                        cupCake.likeCount,
                        ExpressionUtils.as(
                                JPAExpressions
                                        .select()
                                        .from(heart)
                                        .where(
                                                heart.member.email.eq(email),
                                                heart.cupCake.member.email.eq(email)
                                        )
                                        .exists(),
                                "like"
                        )))
                .from(cupCake)
                .where(
                        cupCake.member.email.eq(email),
                        Expressions.numberTemplate(Integer.class, "YEAR({0})", cupCake.createdAt).eq(year),
                        Expressions.numberTemplate(Integer.class, "MONTH({0})", cupCake.createdAt).eq(month)
                )
                .fetch();
    }

    @Override
    public List<CupCakeAccessResponse> findByMemberAndYearMonthAndAccessRange(String email, int year, int month, AccessRange accessRange) {

        return queryFactory
                .select(Projections.constructor(CupCakeAccessResponse.class,
                        cupCake.id,
                        cupCake.emotion,
                        cupCake.createdAt,
                        cupCake.accessRange))
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
                        cupCake.member.nickname,
                        cupCake.createdAt,
                        cupCake.accessRange,
                        cupCake.emotion,
                        cupCake.content,
                        cupCake.likeCount,
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

    @Override
    public List<CupCake> findByEmail(String email) {
        return queryFactory.selectFrom(cupCake)
                .join(cupCake.member, member)
                .where(member.email.eq(email))
                .fetch();
    }

    @Override
    public boolean isExistByLocalDate(Member member, LocalDate date) {
        return queryFactory.
                selectFrom(cupCake)
                .where(cupCake.member.eq(member)
                        .and(cupCake.createdAt.year().eq(date.getYear()))
                        .and(cupCake.createdAt.month().eq(date.getMonthValue()))
                        .and(cupCake.createdAt.dayOfMonth().eq(date.getDayOfMonth())))
                .fetchFirst() != null;
    }
}
