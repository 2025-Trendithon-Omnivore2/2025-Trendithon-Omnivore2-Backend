package com.example.test.omnivore2trendithon2025.heart.domain.repository;


import static com.example.test.omnivore2trendithon2025.cake.domain.QCake.cake;
import static com.example.test.omnivore2trendithon2025.heart.domain.QHeart.heart;

import com.example.test.omnivore2trendithon2025.cake.domain.Cake;
import com.example.test.omnivore2trendithon2025.cake.exception.CakeNotFoundException;
import com.example.test.omnivore2trendithon2025.cupcake.domain.CupCake;
import com.example.test.omnivore2trendithon2025.cupcake.exception.CupCakeNotFoundException;
import com.example.test.omnivore2trendithon2025.heart.domain.Heart;
import com.example.test.omnivore2trendithon2025.member.domain.Member;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class HeartCustomRepositoryImpl implements HeartCustomRepository {

    private final JPAQueryFactory queryFactory;
    private final EntityManager entityManager;

    @Override
    @Transactional
    public void createOrDeleteCakeHeart(Member member, Long cakeId) {
        boolean exists = queryFactory
                .selectOne()
                .from(heart)
                .where(heart.member.eq(member).and(heart.cake.id.eq(cakeId)))
                .fetchFirst() != null;

        Cake targetCake = entityManager.find(Cake.class, cakeId);
        if (targetCake == null) {
            throw new CakeNotFoundException();
        }

        if (!exists) { // 카운트 증가
            Heart newHeart = new Heart(member, targetCake, null);
            entityManager.persist(newHeart);

            targetCake.increaseLikeCount();
        } else { // 카운트 감소
            queryFactory
                    .delete(heart)
                    .where(heart.member.eq(member).and(heart.cake.id.eq(cakeId)))
                    .execute();

            targetCake.decreaseLikeCount();
        }
    }

    @Override
    @Transactional
    public void createOrDeleteCupCakeHeart(Member member, Long cupCakeId) {
        boolean exists = queryFactory
                .selectOne()
                .from(heart)
                .where(heart.member.eq(member).and(heart.cupCake.id.eq(cupCakeId)))
                .fetchFirst() != null;

        CupCake targetCupCake = entityManager.find(CupCake.class, cupCakeId);
        if (targetCupCake == null) {
            throw new CupCakeNotFoundException();
        }

        if (!exists) { // 카운트 증가
            Heart newHeart = new Heart(member, null, targetCupCake);
            entityManager.persist(newHeart);

            targetCupCake.increaseLikeCount();
        } else { // 카운트 감소
            queryFactory
                    .delete(heart)
                    .where(heart.member.eq(member).and(heart.cupCake.id.eq(cupCakeId)))
                    .execute();

            targetCupCake.decreaseLikeCount();
        }
    }

    @Override
    public boolean existsByMemberAndCakeId(Member member, Long cakeId) {
        return queryFactory
                .selectOne()
                .from(heart)
                .where(heart.member.eq(member).and(heart.cake.id.eq(cakeId)))
                .fetchFirst() != null;
    }

    @Override
    public boolean existsByMemberAndCupCakeId(Member member, Long cupCakeId) {
        return queryFactory
                .selectOne()
                .from(heart)
                .where(heart.member.eq(member).and(heart.cake.id.eq(cupCakeId)))
                .fetchFirst() != null;
    }

    @Override
    public List<Boolean> findHeartsForCakes(List<Cake> cakes, Member member) {
        if (cakes == null || cakes.isEmpty()) {
            return Collections.emptyList();
        }

        List<Long> cakeIds = cakes.stream()
                .map(Cake::getId)
                .toList();

        return cakes.stream()
                .map(cake -> queryFactory
                        .selectFrom(heart)
                        .where(heart.member.eq(member)
                                .and(heart.cake.id.eq(cake.getId())))
                        .fetchFirst() != null)
                .collect(Collectors.toList());
    }


}
