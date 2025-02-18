package com.example.test.omnivore2trendithon2025.cupcake.application;

import com.example.test.omnivore2trendithon2025.cupcake.api.dto.request.SaveCupCakeRequest;
import com.example.test.omnivore2trendithon2025.cupcake.api.dto.response.CupCakeResponse;
import com.example.test.omnivore2trendithon2025.cupcake.api.dto.response.SaveCupCakeResponse;
import com.example.test.omnivore2trendithon2025.cupcake.domain.AccessRange;
import com.example.test.omnivore2trendithon2025.cupcake.domain.CupCake;
import com.example.test.omnivore2trendithon2025.cupcake.domain.repository.CupCakeRepository;
import com.example.test.omnivore2trendithon2025.cupcake.exception.CupCakeNotFoundException;
import com.example.test.omnivore2trendithon2025.cupcake.exception.LowCupCakeAccessRoleException;
import com.example.test.omnivore2trendithon2025.member.domain.Member;
import com.example.test.omnivore2trendithon2025.member.domain.repository.MemberRepository;
import com.example.test.omnivore2trendithon2025.member.exception.MemberNotFoundException;
import com.example.test.omnivore2trendithon2025.member.follow.domain.repository.FollowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.YearMonth;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CupCakeService {

    private final CupCakeRepository cupCakeRepository;
    private final MemberRepository memberRepository;
    private final FollowRepository followRepository;

    @Transactional
    public SaveCupCakeResponse saveCupCake(String email, SaveCupCakeRequest dto) {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(MemberNotFoundException::new);

        CupCake newCupCake = CupCake.createCupCake(member, dto.emotion(), dto.content(), dto.accessRange());

        cupCakeRepository.save(newCupCake);

        return SaveCupCakeResponse.builder()
                .cupCakeId(newCupCake.getId())
                .build();
    }

    public CupCakeResponse findCupCake(String email, Long cupCakeId) {
        CupCake target = cupCakeRepository.findById(cupCakeId)
                .orElseThrow(CupCakeNotFoundException::new);

        // 내 컵케이크: 즉시 조회
        if(isMine(email, target.getMember().getEmail())) return CupCakeToResponse(target);

        // 내 컵케이크 x: 조건 확인 후 처리
        return switch (target.getAccessRange()) {
            case PUBLIC -> CupCakeToResponse(target);
            case FRIEND -> {
                if (existsFollow(email, target.getMember().getEmail()))
                    yield CupCakeToResponse(target);
                throw new LowCupCakeAccessRoleException();
            }
            default -> throw new LowCupCakeAccessRoleException();
        };

    }

    public List<CupCakeResponse> findMyCupCakes(String email, YearMonth yearMonth) {
        return cupCakeRepository.findByMemberAndYearMonth(email, yearMonth.getYear(), yearMonth.getMonthValue());
    }

    public List<CupCakeResponse> findByAccessRangeAndEmail(AccessRange accessRange, String email) {
        return List.of();
    }

    private CupCakeResponse CupCakeToResponse(CupCake target) {
        return CupCakeResponse.of(target.getEmotion(),
                    target.getContent(),
                    target.getCreatedAt(),
                    target.getAccessRange(),
                    target.getLikeCount());
    }

    private boolean isMine(String email, String targetEmail) {
        return email.equals(targetEmail);
    }

    private boolean existsFollow(String clientEmail, String targetEmail) {
        return followRepository.existsByFromMemberAndToMember(
                memberRepository.findByEmail(clientEmail)
                        .orElseThrow(MemberNotFoundException::new),
                memberRepository.findByEmail(targetEmail)
                        .orElseThrow(MemberNotFoundException::new)
        );
    }
}
