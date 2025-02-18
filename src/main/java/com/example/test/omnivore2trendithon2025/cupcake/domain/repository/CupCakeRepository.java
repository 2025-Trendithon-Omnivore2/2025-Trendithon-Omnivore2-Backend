package com.example.test.omnivore2trendithon2025.cupcake.domain.repository;

import com.example.test.omnivore2trendithon2025.cupcake.api.dto.response.CupCakeResponse;
import com.example.test.omnivore2trendithon2025.cupcake.domain.CupCake;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CupCakeRepository extends JpaRepository<CupCake, Long>{
    @Query("SELECT new com.example.test.omnivore2trendithon2025.cupcake" +
            ".api.dto.response.CupCakeResponse(" +
            "c.id, c.emotion, c.content, c.createdAt, c.accessRange, c.likeCount) FROM CupCake c " +
            "WHERE c.member.email = :email " +
            "AND FUNCTION('YEAR', c.createdAt) = :year " +
            "AND FUNCTION('MONTH', c.createdAt) = :month")
    List<CupCakeResponse> findByMemberAndYearMonth(@Param("email") String email,
                                                   @Param("year") int year,
                                                   @Param("month") int month);
}
