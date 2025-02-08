package com.example.test.omnivore2trendithon2025.auth.application;

import com.example.test.omnivore2trendithon2025.auth.api.dto.response.IdTokenResDto;
import com.example.test.omnivore2trendithon2025.auth.api.dto.response.UserInfo;

public interface AuthService {
    UserInfo getUserInfo(String authCode);

    String getProvider();

    IdTokenResDto getIdToken(String code);
}
