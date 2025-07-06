package com.codewithproject.gameparty.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codewithproject.gameparty.entity.Milionerzy;

@Repository
public interface MilionerzyRepository extends JpaRepository<Milionerzy, Long> {

    List<Milionerzy> findByLevel(Integer level);

    long countByLevel(Integer level);

}
