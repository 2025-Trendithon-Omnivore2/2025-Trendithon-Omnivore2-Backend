package com.example.test.omnivore2trendithon2025.auth.application;

import com.example.test.omnivore2trendithon2025.auth.api.dto.response.MemberLoginResDto;
import com.example.test.omnivore2trendithon2025.auth.api.dto.response.UserInfo;
import com.example.test.omnivore2trendithon2025.auth.exception.EmailNotFoundException;
import com.example.test.omnivore2trendithon2025.auth.exception.ExistsMemberEmailException;
import com.example.test.omnivore2trendithon2025.global.entity.Status;
import com.example.test.omnivore2trendithon2025.member.domain.Member;
import com.example.test.omnivore2trendithon2025.member.domain.Role;
import com.example.test.omnivore2trendithon2025.member.domain.SocialType;
import com.example.test.omnivore2trendithon2025.member.domain.repository.MemberRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthMemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public MemberLoginResDto saveUserInfo(UserInfo userInfo, SocialType provider) {
        validateNotFoundEmail(userInfo.email());

        Member member = getExistingMemberOrCreateNew(userInfo, provider);

        validateSocialType(member, provider);

        return MemberLoginResDto.from(member);
    }

    private void validateNotFoundEmail(String email) {
        if (email == null) {
            throw new EmailNotFoundException();
        }
    }

    private Member getExistingMemberOrCreateNew(UserInfo userInfo, SocialType provider) {
        return memberRepository.findByEmail(userInfo.email()).orElseGet(() -> createMember(userInfo, provider));
    }

    private Member createMember(UserInfo userInfo, SocialType provider) {
        String userPicture = getUserPicture(userInfo.picture());

        return memberRepository.save(
                Member.builder()
                        .status(Status.ACTIVE)
                        .email(userInfo.email())
                        .name(userInfo.nickname())
                        .picture(userPicture)
                        .socialType(provider)
                        .role(Role.ROLE_USER)
                        .firstLogin(true)
                        .introduction("")
                        .build()
        );
    }

    private String getUserPicture(String picture) {
        return Optional.ofNullable(picture)
                .map(this::convertToHighRes)
                .orElseThrow();
    }

    private String convertToHighRes(String url){
        return url.replace("s96-c", "s2048-c");
    }

    private void validateSocialType(Member member, SocialType provider) {
        if (!provider.equals(member.getSocialType())) {
            throw new ExistsMemberEmailException();
        }
    }
}
