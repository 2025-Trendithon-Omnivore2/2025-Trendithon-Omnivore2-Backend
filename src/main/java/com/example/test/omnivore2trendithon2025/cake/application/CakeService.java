package com.example.test.omnivore2trendithon2025.cake.application;

import com.example.test.omnivore2trendithon2025.cake.api.dto.request.SurveyRequest;
import com.example.test.omnivore2trendithon2025.cake.domain.Cake;
import com.example.test.omnivore2trendithon2025.cake.domain.CakeColor;
import com.example.test.omnivore2trendithon2025.cake.domain.repository.CakeRepository;
import com.example.test.omnivore2trendithon2025.cake.exception.WrongSurveyResultException;
import com.example.test.omnivore2trendithon2025.member.domain.Member;
import com.example.test.omnivore2trendithon2025.member.domain.repository.MemberRepository;
import com.example.test.omnivore2trendithon2025.member.exception.MemberNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.test.omnivore2trendithon2025.cake.domain.CakeColor.*;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CakeService {

    private final CakeRepository cakeRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public Long makeCake(SurveyRequest dto) {
        Member member = memberRepository.findById(dto.memberId())
                .orElseThrow(MemberNotFoundException::new);

        CakeColor color = determineCakeColor(dto.answer1(), dto.answer2(), dto.answer3());

        Cake cake = Cake.makeNewCake(member, color);

        return cakeRepository.save(cake)
                .getId();
    }

    private CakeColor determineCakeColor(String a1, String a2, String a3) {
        return switch (a1+a2+a3) {
            case "aaa" -> WHITE;
            case "aab" -> STRAWBERRY;
            case "aba" -> YELLOW; // 3~5번은 내용 바뀌면 수정
            case "baa" -> GREEN;
            case "abb" -> BLUE;
            case "bab" -> LEMON;
            case "bba" -> PISTACHIO;
            case "bbb" -> CHOCOLATE;
            default -> throw new WrongSurveyResultException();
        };
    }
}
