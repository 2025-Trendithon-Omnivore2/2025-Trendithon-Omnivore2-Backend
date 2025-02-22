package com.example.test.omnivore2trendithon2025.cake.domain.repository;

import com.example.test.omnivore2trendithon2025.cake.domain.Cake;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CakeRepository extends JpaRepository<Cake, Long>, CakeCustomRepository {

}
