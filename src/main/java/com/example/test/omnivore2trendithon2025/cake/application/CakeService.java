package com.example.test.omnivore2trendithon2025.cake.application;

import com.example.test.omnivore2trendithon2025.cake.api.dto.request.SurveyRequest;
import com.example.test.omnivore2trendithon2025.cake.api.dto.response.CakeResponse;
import com.example.test.omnivore2trendithon2025.cake.domain.Cake;
import com.example.test.omnivore2trendithon2025.cake.domain.CakeColor;
import com.example.test.omnivore2trendithon2025.cake.domain.cakecandle.api.dto.response.CakeCandleResponse;
import com.example.test.omnivore2trendithon2025.cake.domain.repository.CakeRepository;
import com.example.test.omnivore2trendithon2025.cake.exception.CakeNotFoundException;
import com.example.test.omnivore2trendithon2025.cake.exception.WrongSurveyResultException;
import com.example.test.omnivore2trendithon2025.member.domain.Member;
import com.example.test.omnivore2trendithon2025.member.domain.repository.MemberRepository;
import com.example.test.omnivore2trendithon2025.member.exception.MemberNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

import static com.example.test.omnivore2trendithon2025.cake.domain.CakeColor.*;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CakeService {

    private final CakeRepository cakeRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public Long makeCake(String email, SurveyRequest dto) {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(MemberNotFoundException::new);

        CakeColor color = determineCakeColor(dto.answer1(), dto.answer2(), dto.answer3());

        Cake cake = Cake.makeNewCake(member, color);

        return cakeRepository.save(cake)
                .getId();
    }

    public CakeResponse findByCakeId(Long id) {
        Cake cake = cakeRepository.findById(id)
                .orElseThrow(CakeNotFoundException::new);

        return getCakeResponse(cake);
    }

    public CakeResponse findByMemberEmail(String email) {
        Cake cake = cakeRepository.findByMemberEmail(email)
                .orElseThrow(CakeNotFoundException::new);

        return getCakeResponse(cake);
    }

    private CakeColor determineCakeColor(String a1, String a2, String a3) {
        return switch (a1+a2+a3) {
            case "aaa" -> STRAWBERRY;
            case "aab" -> LEMON;
            case "aba" -> GREEN_TEA;
            case "baa" -> TIRAMISU;
            case "abb" -> CREAM;
            case "bab" -> BLUEBERRY;
            case "bba" -> PISTACHIO;
            case "bbb" -> CHOCOLATE;
            default -> throw new WrongSurveyResultException();
        };
    }

    private CakeResponse getCakeResponse(Cake cake) {
        return CakeResponse.builder()
                .color(cake.getColor())
                .candles(cake.getCandles()
                        .stream()
                        .map(candle -> CakeCandleResponse.builder()
                                .imgUrl(candle.getImgUrl())
                                .content(candle.getContent())
                                .build())
                        .collect(Collectors.toList()))
                .build();
    }
}
