package com.example.test.omnivore2trendithon2025.cake.application;

import com.example.test.omnivore2trendithon2025.cake.api.dto.request.SurveyRequest;
import com.example.test.omnivore2trendithon2025.cake.api.dto.response.GuestCakeResponse;
import com.example.test.omnivore2trendithon2025.cake.api.dto.response.MyCakeResponse;
import com.example.test.omnivore2trendithon2025.cake.api.dto.response.OtherCakeResponse;
import com.example.test.omnivore2trendithon2025.cake.domain.Cake;
import com.example.test.omnivore2trendithon2025.cake.domain.CakeColor;
import com.example.test.omnivore2trendithon2025.cake.domain.repository.CakeRepository;
import com.example.test.omnivore2trendithon2025.cake.exception.CakeNotFoundException;
import com.example.test.omnivore2trendithon2025.cake.exception.WrongSurveyResultException;
import com.example.test.omnivore2trendithon2025.heart.domain.repository.HeartRepository;
import com.example.test.omnivore2trendithon2025.member.domain.Member;
import com.example.test.omnivore2trendithon2025.member.domain.repository.MemberRepository;
import com.example.test.omnivore2trendithon2025.member.exception.MemberNotFoundException;
import com.example.test.omnivore2trendithon2025.member.follow.api.dto.response.FollowInfoResDto;
import com.example.test.omnivore2trendithon2025.member.follow.domain.repository.FollowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.example.test.omnivore2trendithon2025.cake.domain.CakeColor.*;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CakeService {

    private final CakeRepository cakeRepository;
    private final MemberRepository memberRepository;
    private final FollowRepository followRepository;
    private final HeartRepository heartRepository;

    @Transactional
    public Long makeCake(String email, SurveyRequest dto) {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(MemberNotFoundException::new);

        CakeColor color = dto.color();

        Cake cake = Cake.makeNewCake(member, color);

        return cakeRepository.save(cake)
                .getId();
    }

    public OtherCakeResponse findByCakeId(Long cakeId, String email) {
        Cake cake = cakeRepository.findById(cakeId)
                .orElseThrow(CakeNotFoundException::new);

        boolean like = heartRepository.existsByMemberAndCakeId(
                memberRepository.findByEmail(email)
                        .orElseThrow(MemberNotFoundException::new), cakeId);

        return OtherCakeResponse.of(cake.getId(),
                cake.getMember().getNickname(),
                cake.getColor(),
                cake.getCandles(),
                cake.getLikeCount(),
                like);
    }

    public MyCakeResponse findByMemberEmail(String email) {
        Cake cake = cakeRepository.findByMemberEmail(email)
                .orElseThrow(CakeNotFoundException::new);

        return MyCakeResponse.of(cake.getId(),
                cake.getColor(),
                cake.getCandles(),
                cake.getLikeCount());
    }

    public OtherCakeResponse findByMemberId(String email, Long memberId){
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(MemberNotFoundException::new);

        return cakeRepository.findByMemberId(member, memberId)
                .orElseThrow(CakeNotFoundException::new);
    }

    public List<OtherCakeResponse> findFollowerCakes(String email, Pageable pageable) {
        Member member = memberRepository.findByEmail(email)
                        .orElseThrow(MemberNotFoundException::new);

        List<Long> followerIds = followRepository.findFollowList(member.getId(), pageable)
                .getContent()
                .stream()
                .map(FollowInfoResDto::memberId)
                .toList();

        return cakeRepository.findFollowerCakes(member, followerIds);
    }

    public GuestCakeResponse findShareCake(Long memberId) {
         return cakeRepository.findByOnlyMemberId(memberId)
                .orElseThrow(CakeNotFoundException::new);
    }
}
