package com.example.test.omnivore2trendithon2025.cake.domain.repository;

import com.example.test.omnivore2trendithon2025.cake.domain.Cake;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import org.springframework.data.repository.query.Param;

public interface CakeRepository extends JpaRepository<Cake, Long>, CakeCustomRepository {

    @Query("SELECT c.id FROM Cake c WHERE c.member.id = :memberId")
    Optional<Long> findIdByMemberId(Long memberId);

}
