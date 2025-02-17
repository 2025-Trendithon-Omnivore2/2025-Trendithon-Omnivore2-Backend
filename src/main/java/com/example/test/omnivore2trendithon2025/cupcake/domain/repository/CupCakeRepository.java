package com.example.test.omnivore2trendithon2025.cupcake.domain.repository;

import com.example.test.omnivore2trendithon2025.cupcake.domain.AccessRange;
import com.example.test.omnivore2trendithon2025.cupcake.domain.CupCake;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CupCakeRepository extends JpaRepository<CupCake, Long> {
    List<CupCake> findByAccessRangeAndMember_Email(AccessRange range, String email);
}
