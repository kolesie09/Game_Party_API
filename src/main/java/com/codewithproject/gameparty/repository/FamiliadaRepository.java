package com.codewithproject.gameparty.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.codewithproject.gameparty.entity.Familiada;

@Repository
public interface FamiliadaRepository extends JpaRepository<Familiada, Long> {

    @Query(value = "SELECT * FROM familiada ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Familiada findRandomQuestion();

}
