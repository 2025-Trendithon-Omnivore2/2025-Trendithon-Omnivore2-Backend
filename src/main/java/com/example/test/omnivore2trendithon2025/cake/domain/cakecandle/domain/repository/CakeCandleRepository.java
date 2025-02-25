package com.example.test.omnivore2trendithon2025.cake.domain.cakecandle.domain.repository;

import com.example.test.omnivore2trendithon2025.cake.domain.Cake;
import com.example.test.omnivore2trendithon2025.cake.domain.cakecandle.domain.CakeCandle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CakeCandleRepository extends JpaRepository<CakeCandle, Long> {
    @Query("SELECT cc FROM CakeCandle cc JOIN cc.cake c WHERE c.id =:id")
    List<CakeCandle> findByCakeId(@Param("id") Long cakeId);

    @Query("SELECT cc FROM CakeCandle cc JOIN cc.cake c where c.id =:id and cc.candleIndex =:candleIndex")
    Optional<CakeCandle> findByCakeAndIndex(Long cakeId, Integer candleIndex);
}
