package com.codewithproject.gameparty.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codewithproject.gameparty.entity.MilionerzyAnswer;

@Repository
public interface MilionerzyAnswerRepository extends JpaRepository<MilionerzyAnswer, Long> {

    List<MilionerzyAnswer> findByMilionerzyId(Long milionerzyId);

}
