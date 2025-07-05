package com.codewithproject.gameparty.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codewithproject.gameparty.entity.FamiliadaAnswer;

@Repository
public interface FamiliadaAnswerRepository extends JpaRepository<FamiliadaAnswer, Long> {

    List<FamiliadaAnswer> findByFamiliadaId(Long familiadaId);
}
