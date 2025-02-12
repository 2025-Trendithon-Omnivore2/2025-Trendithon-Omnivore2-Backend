package com.example.test.omnivore2trendithon2025.member.follow.domain.repository;

import com.example.test.omnivore2trendithon2025.member.follow.domain.Follow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowRepository extends JpaRepository<Follow, Long>, FollowCustomRepository {
}
