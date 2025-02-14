package com.example.test.omnivore2trendithon2025.member.follow.domain.repository;

import static com.example.test.omnivore2trendithon2025.member.domain.QMember.member;
import static com.example.test.omnivore2trendithon2025.member.follow.domain.QFollow.follow;
import com.example.test.omnivore2trendithon2025.member.domain.Member;
import com.example.test.omnivore2trendithon2025.member.follow.api.dto.response.FollowInfoResDto;
import com.example.test.omnivore2trendithon2025.member.follow.api.dto.response.MemberInfoForFollowResDto;
import com.example.test.omnivore2trendithon2025.member.follow.domain.Follow;
import com.example.test.omnivore2trendithon2025.member.follow.domain.FollowStatus;
import com.example.test.omnivore2trendithon2025.member.follow.exception.FollowAlreadyAcceptException;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.jpa.impl.JPAUpdateClause;
import jakarta.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FollowCustomRepositoryImpl implements FollowCustomRepository {

    private final JPAQueryFactory queryFactory;
    private final EntityManager entityManager;

    @Override
    public boolean existsByFromMemberAndToMember(Member fromMember, Member toMember) {
        Integer fetchOne = queryFactory
                .selectOne()
                .from(follow)
                .where((follow.fromMember.eq(fromMember)
                        .and(follow.toMember.eq(toMember)))
                        .or(follow.fromMember.eq(toMember)
                                .and(follow.toMember.eq(fromMember))))
                .fetchFirst();

        return fetchOne != null;
    }

    @Override
    public void acceptFollowingRequest(Long followId) {
        checkIfAlreadyAccepted(followId);

        new JPAUpdateClause(entityManager, follow)
                .where(follow.id.eq(followId))
                .set(follow.followStatus, FollowStatus.ACCEPT)
                .execute();
    }

    private void checkIfAlreadyAccepted(Long followId) {
        FollowStatus currentStatus = new JPAQuery<>(entityManager)
                .select(follow.followStatus)
                .from(follow)
                .where(follow.id.eq(followId))
                .fetchOne();

        if (currentStatus == FollowStatus.ACCEPT) {
            throw new FollowAlreadyAcceptException();
        }
    }

    @Override
    public Page<FollowInfoResDto> findFollowList(Long memberId, Pageable pageable) {
        List<FollowInfoResDto> fetch = queryFactory
                .selectFrom(follow)
                .where(follow.fromMember.id.eq(memberId)
                        .or(follow.toMember.id.eq(memberId))
                        .and(follow.followStatus.eq(FollowStatus.ACCEPT)))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch()
                .stream()
                .map(follow -> FollowInfoResDto.of(follow, memberId))
                .collect(Collectors.toList());

        long total = Optional.ofNullable(queryFactory
                .select(follow.count())
                .from(follow)
                .where(follow.fromMember.id.eq(memberId)
                        .or(follow.toMember.id.eq(memberId))
                        .and(follow.followStatus.eq(FollowStatus.ACCEPT)))
                .fetchOne()).orElse(0L);

        return new PageImpl<>(fetch, pageable, total);
    }

    @Override
    public Optional<Follow> findByFromMemberAndToMember(Member fromMember, Member toMember) {
        Follow followRecord = queryFactory
                .selectFrom(follow)
                .where(
                        (follow.fromMember.eq(fromMember).and(follow.toMember.eq(toMember)))
                                .or(follow.fromMember.eq(toMember).and(follow.toMember.eq(fromMember))) // 양방향 조회
                )
                .fetchOne();

        return Optional.ofNullable(followRecord);
    }

    @Override
    public boolean existsAlreadyFollow(Long followId) {
        return queryFactory
                .selectOne()
                .from(follow)
                .where(follow.id.eq(followId)
                        .and(follow.followStatus.eq(FollowStatus.ACCEPT)))
                .fetchFirst() != null;
    }

    @Override
    public Page<FollowInfoResDto> findFollowerRequestList(Long memberId, Pageable pageable) {

        List<FollowInfoResDto> fetch = queryFactory
                .select(Projections.constructor(FollowInfoResDto.class,
                        follow.fromMember.id,
                        follow.fromMember.name,
                        follow.fromMember.nickname,
                        follow.fromMember.picture
                ))
                .from(follow)
                .join(follow.fromMember, member)
                .where(follow.toMember.id.eq(memberId)
                        .and(follow.followStatus.eq(FollowStatus.WAIT)))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total = Optional.ofNullable(
                queryFactory
                        .select(follow.count())
                        .from(follow)
                        .where(follow.toMember.id.eq(memberId)
                                .and(follow.followStatus.eq(FollowStatus.WAIT)))
                        .fetchOne()
        ).orElse(0L);

        return new PageImpl<>(fetch, pageable, total);
    }
}
