package com.example.test.omnivore2trendithon2025.heart.domain.repository;

import com.example.test.omnivore2trendithon2025.heart.domain.Heart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HeartRepository extends JpaRepository<Heart, Long>, HeartCustomRepository {
}
