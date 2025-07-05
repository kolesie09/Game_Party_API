package com.codewithproject.gameparty.service;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.codewithproject.gameparty.dto.AnswerDto;
import com.codewithproject.gameparty.dto.QuestionDto;
import com.codewithproject.gameparty.entity.Milionerzy;
import com.codewithproject.gameparty.repository.MilionerzyAnswerRepository;
import com.codewithproject.gameparty.repository.MilionerzyRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MilionerzyService {

    private final MilionerzyRepository milionerzyRepository;
    private final MilionerzyAnswerRepository milionerzyAnswerRepository;
    private final MilionerzyAnswerService milionerzyAnswerService;

    public Optional<QuestionDto> getRandomQuestionByLevel(int level) {
        List<Milionerzy> questions = milionerzyRepository.findByLevel(level);
        if (questions.isEmpty()) {
            return Optional.empty();
        }
        Milionerzy question = questions.get(new Random().nextInt(questions.size()));

        List<AnswerDto> answerDtos = milionerzyAnswerService.findAnswersByQuestionId(question.getId()).stream()
                .map(a -> new AnswerDto(a.getId(), a.getName()))
                .collect(Collectors.toList());

        QuestionDto dto = new QuestionDto(
                question.getId(),
                question.getName(),
                question.getCorrectAnswer() != null ? question.getCorrectAnswer().getId() : null,
                answerDtos
        );

        return Optional.of(dto);
    }
}
