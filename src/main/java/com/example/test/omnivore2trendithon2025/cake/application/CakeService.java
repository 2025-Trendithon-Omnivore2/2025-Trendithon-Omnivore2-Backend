package com.example.test.omnivore2trendithon2025.cake.application;

import com.example.test.omnivore2trendithon2025.cake.domain.repository.CakeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CakeService {
    private final CakeRepository CakeRepository;
}
