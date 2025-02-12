package com.example.test.omnivore2trendithon2025.member.follow.application;

import com.example.test.omnivore2trendithon2025.global.dto.PageInfoResDto;
import com.example.test.omnivore2trendithon2025.member.domain.Member;
import com.example.test.omnivore2trendithon2025.member.domain.repository.MemberRepository;
import com.example.test.omnivore2trendithon2025.member.exception.MemberNotFoundException;
import com.example.test.omnivore2trendithon2025.member.follow.api.dto.request.FollowReqDto;
import com.example.test.omnivore2trendithon2025.member.follow.api.dto.response.FollowInfoListDto;
import com.example.test.omnivore2trendithon2025.member.follow.api.dto.response.FollowInfoResDto;
import com.example.test.omnivore2trendithon2025.member.follow.api.dto.response.FollowResDto;
import com.example.test.omnivore2trendithon2025.member.follow.domain.Follow;
import com.example.test.omnivore2trendithon2025.member.follow.domain.repository.FollowRepository;
import com.example.test.omnivore2trendithon2025.member.follow.exception.AlreadyFriendsException;
import com.example.test.omnivore2trendithon2025.member.follow.exception.FollowAlreadyExistsException;
import com.example.test.omnivore2trendithon2025.member.follow.exception.FollowNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FollowService {

    private final MemberRepository memberRepository;
    private final FollowRepository followRepository;

    @Transactional
    public FollowResDto save(String email, FollowReqDto followReqDto) {
        Member fromMember = memberRepository.findByEmail(email).orElseThrow(MemberNotFoundException::new);
        Member toMember = memberRepository.findByEmail(followReqDto.email()).orElseThrow(FollowNotFoundException::new);

        validateFollowDoesNotExist(fromMember, toMember);

        Follow follow = followReqDto.toEntity(fromMember, toMember);
        followRepository.save(follow);

        return FollowResDto.from(toMember);
    }

    private void validateFollowDoesNotExist(Member fromMember, Member toMember) {
        if (followRepository.existsByFromMemberAndToMember(fromMember, toMember)) {
            throw new FollowAlreadyExistsException();
        }
    }

    @Transactional
    public void accept(Long followId) {
        validateFollowStatusIsAccept(followId);

        followRepository.acceptFollowingRequest(followId);
    }

    private void validateFollowStatusIsAccept(Long followId) {
        if (followRepository.existsAlreadyFollow(followId)) {
            throw new AlreadyFriendsException();
        }
    }

    public FollowInfoListDto findFollowList(String email, Pageable pageable) {
        Long memberId = memberRepository.findByEmail(email).orElseThrow(MemberNotFoundException::new).getId();

        Page<FollowInfoResDto> followInfoResDtos = followRepository.findFollowList(memberId, pageable);

        return FollowInfoListDto.of(
                followInfoResDtos.getContent(),
                PageInfoResDto.from(followInfoResDtos)
        );
    }

    @Transactional
    public void delete(String email, Long memberId) {
        Member member = memberRepository.findByEmail(email).orElseThrow(MemberNotFoundException::new);
        Member toMemberEntity = memberRepository.findById(memberId).orElseThrow(MemberNotFoundException::new);

        Follow follow = followRepository.findByFromMemberAndToMember(member, toMemberEntity)
                .orElseThrow(FollowNotFoundException::new);

        followRepository.delete(follow);
    }
}
