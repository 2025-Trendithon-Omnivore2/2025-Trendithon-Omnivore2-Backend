package com.example.test.omnivore2trendithon2025.heart.domain.repository;


import static com.example.test.omnivore2trendithon2025.cake.domain.QCake.cake;
import static com.example.test.omnivore2trendithon2025.heart.domain.QHeart.heart;

import com.example.test.omnivore2trendithon2025.cake.domain.Cake;
import com.example.test.omnivore2trendithon2025.cake.exception.CakeNotFoundException;
import com.example.test.omnivore2trendithon2025.heart.domain.Heart;
import com.example.test.omnivore2trendithon2025.member.domain.Member;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class HeartCustomRepositoryImpl implements HeartCustomRepository {

    private final JPAQueryFactory queryFactory;
    private final EntityManager entityManager;

    @Override
    @Transactional
    public void createOrDeleteHeart(Member member, Long cakeId) {
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
            Heart newHeart = new Heart(member, targetCake);
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
}
