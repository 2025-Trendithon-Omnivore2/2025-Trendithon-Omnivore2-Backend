package com.example.test.omnivore2trendithon2025.global.config;

import com.example.test.omnivore2trendithon2025.global.filter.LogFilter;
import com.example.test.omnivore2trendithon2025.global.filter.LoginCheckFilter;
import com.example.test.omnivore2trendithon2025.global.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class FilterWebConfig {

    private final TokenProvider tokenProvider;

    @Bean
    public FilterRegistrationBean<LogFilter> logFilter() {
        FilterRegistrationBean<LogFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new LogFilter()); // 여기서 만든 필터 클래스 등록
        filterRegistrationBean.setOrder(1);
        filterRegistrationBean.addUrlPatterns("/*");
        return filterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean<LoginCheckFilter> loginCheckFilter() {
        FilterRegistrationBean<LoginCheckFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new LoginCheckFilter(tokenProvider)); // JWT 토큰 유효성 검사를 위한 필터 클래스 등록
        filterRegistrationBean.setOrder(2); // 1번인 로그필터 다음으로 수행
        filterRegistrationBean.addUrlPatterns("/*");
        return filterRegistrationBean;
    }
}