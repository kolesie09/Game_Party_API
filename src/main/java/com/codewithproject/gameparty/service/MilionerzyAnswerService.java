package com.codewithproject.gameparty.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.codewithproject.gameparty.entity.MilionerzyAnswer;
import com.codewithproject.gameparty.repository.MilionerzyAnswerRepository;
import com.codewithproject.gameparty.repository.MilionerzyRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MilionerzyAnswerService {

    private final MilionerzyRepository milionerzyRepository;
    private final MilionerzyAnswerRepository milionerzyAnswerRepository;

    // Pobierz odpowiedzi do pytania po jego id
    public List<MilionerzyAnswer> findAnswersByQuestionId(Long questionId) {
        return milionerzyAnswerRepository.findByMilionerzyId(questionId);
    }

    // Znajdź konkretną odpowiedź po id
    public Optional<MilionerzyAnswer> findAnswerById(Long id) {
        return milionerzyAnswerRepository.findById(id);
    }

    // Zapisz nową odpowiedź
    public MilionerzyAnswer saveAnswer(MilionerzyAnswer answer) {
        return milionerzyAnswerRepository.save(answer);
    }

    // Usuń odpowiedź po id
    public void deleteAnswer(Long id) {
        milionerzyAnswerRepository.deleteById(id);
    }
}
