package com.example.test.omnivore2trendithon2025.member.follow.domain.repository;

import com.example.test.omnivore2trendithon2025.member.domain.Member;
import com.example.test.omnivore2trendithon2025.member.follow.api.dto.response.FollowInfoListDto;
import com.example.test.omnivore2trendithon2025.member.follow.api.dto.response.FollowInfoResDto;
import com.example.test.omnivore2trendithon2025.member.follow.api.dto.response.MemberInfoForFollowResDto;
import com.example.test.omnivore2trendithon2025.member.follow.domain.Follow;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FollowCustomRepository {

    boolean existsByFromMemberAndToMember(Member fromMember, Member toMember);

    void acceptFollowingRequest(Long followId);

    Page<FollowInfoResDto> findFollowList(Long memberId, Pageable pageable);

    Optional<Follow> findByFromMemberAndToMember(Member fromMember, Member toMember);

    Page<FollowInfoResDto> findFollowerRequestList(Long memberId, Pageable pageable);

    boolean existsAlreadyFollow(Long followId);
}
