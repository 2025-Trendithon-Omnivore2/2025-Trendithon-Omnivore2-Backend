package com.example.test.omnivore2trendithon2025.cupcake.domain.repository;

import com.example.test.omnivore2trendithon2025.cupcake.api.dto.response.CupCakeResponse;
import com.example.test.omnivore2trendithon2025.cupcake.domain.AccessRange;
import com.example.test.omnivore2trendithon2025.cupcake.domain.QCupCake;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CupCakeCustomRepositoryImpl implements CupCakeCustomRepository {
    private EntityManager em;
    private JPAQueryFactory queryFactory;

    @Override
    public List<CupCakeResponse> findByMemberAndYearMonth(String email, int year, int month) {
        QCupCake cupCake = QCupCake.cupCake;

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
        QCupCake cupCake = QCupCake.cupCake;

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
}
