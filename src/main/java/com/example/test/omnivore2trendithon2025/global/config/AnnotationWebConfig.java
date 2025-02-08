package com.example.test.omnivore2trendithon2025.global.config;

import com.example.test.omnivore2trendithon2025.global.annotationresolver.CurrentUserEmailArgumentResolver;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class AnnotationWebConfig implements WebMvcConfigurer {

    private final CurrentUserEmailArgumentResolver currentUserEmailArgumentResolver;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(currentUserEmailArgumentResolver);
    }
}

