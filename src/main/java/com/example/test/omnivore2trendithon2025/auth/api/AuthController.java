package com.example.test.omnivore2trendithon2025.auth.api;

import com.example.test.omnivore2trendithon2025.auth.api.dto.request.RefreshTokenReqDto;
import com.example.test.omnivore2trendithon2025.auth.api.dto.request.TokenReqDto;
import com.example.test.omnivore2trendithon2025.auth.api.dto.response.IdTokenResDto;
import com.example.test.omnivore2trendithon2025.auth.api.dto.response.MemberLoginResDto;
import com.example.test.omnivore2trendithon2025.auth.api.dto.response.UserInfo;
import com.example.test.omnivore2trendithon2025.auth.application.AuthMemberService;
import com.example.test.omnivore2trendithon2025.auth.application.AuthService;
import com.example.test.omnivore2trendithon2025.auth.application.AuthServiceFactory;
import com.example.test.omnivore2trendithon2025.auth.application.TokenService;
import com.example.test.omnivore2trendithon2025.global.jwt.api.dto.TokenDto;
import com.example.test.omnivore2trendithon2025.global.template.RspTemplate;
import com.example.test.omnivore2trendithon2025.member.domain.SocialType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthController implements AuthDocs {

    private final AuthServiceFactory authServiceFactory;
    private final AuthMemberService memberService;
    private final TokenService tokenService;

    @GetMapping("oauth2/callback/{provider}")
    public IdTokenResDto callback(@PathVariable(name = "provider") String provider,
                                  @RequestParam(name = "code") String code) {
        AuthService authService = authServiceFactory.getAuthService(provider);
        return authService.getIdToken(code);
    }

    @PostMapping("/{provider}/token")
    public RspTemplate<TokenDto> generateAccessAndRefreshToken(
            @PathVariable(name = "provider") String provider,
            @RequestBody TokenReqDto tokenReqDto) {
        AuthService authService = authServiceFactory.getAuthService(provider);
        UserInfo userInfo = authService.getUserInfo(tokenReqDto.authCode());

        MemberLoginResDto getMemberDto = memberService.saveUserInfo(userInfo,
                SocialType.valueOf(provider.toUpperCase()));
        TokenDto getToken = tokenService.getToken(getMemberDto);

        return new RspTemplate<>(HttpStatus.OK, "토큰 발급", getToken);
    }

    @PostMapping("/token/access")
    public RspTemplate<TokenDto> generateAccessToken(@RequestBody RefreshTokenReqDto refreshTokenReqDto) {
        TokenDto getToken = tokenService.generateAccessToken(refreshTokenReqDto);

        return new RspTemplate<>(HttpStatus.OK, "액세스 토큰 발급", getToken);
    }
}