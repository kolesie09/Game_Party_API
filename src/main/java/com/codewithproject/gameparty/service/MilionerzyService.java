package com.codewithproject.gameparty.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.codewithproject.gameparty.dto.AnswerDto;
import com.codewithproject.gameparty.dto.QuestionDto;
import com.codewithproject.gameparty.dto.QuestionDtoLevel;
import com.codewithproject.gameparty.entity.Milionerzy;
import com.codewithproject.gameparty.entity.MilionerzyAnswer;
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

    public QuestionDtoLevel saveQuestionWithAnswers(QuestionDtoLevel dto) {
        try {
            Milionerzy question = new Milionerzy();
            question.setName(dto.getName());
            question.setLevel(dto.getLevel());

            List<MilionerzyAnswer> answers = new ArrayList<>();
            for (AnswerDto answerDto : dto.getAnswers()) {
                MilionerzyAnswer answer = new MilionerzyAnswer();
                answer.setName(answerDto.getName());
                answer.setMilionerzy(question);
                answers.add(answer);
            }
            question.setAnswers(answers);

            Milionerzy saved = milionerzyRepository.save(question);

            if (dto.getCorrectAnswer() != null) {
                MilionerzyAnswer correct = saved.getAnswers().stream()
                        .filter(ans -> ans.getName().equals(dto.getCorrectAnswer()))
                        .findFirst()
                        .orElse(null);

                if (correct == null) {
                    System.out.println("Nie znaleziono poprawnej odpowiedzi w liście!");
                } else {
                    saved.setCorrectAnswer(correct);
                    saved = milionerzyRepository.save(saved);
                }
            }

            List<AnswerDto> answerDtos = saved.getAnswers().stream()
                    .map(a -> new AnswerDto(a.getId(), a.getName()))
                    .collect(Collectors.toList());

            return new QuestionDtoLevel(
                    saved.getId(),
                    saved.getName(),
                    saved.getLevel(),
                    saved.getCorrectAnswer() != null ? saved.getCorrectAnswer().getName() : null,
                    answerDtos
            );
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

    }

    public void updateQuestionLevel(Long id, boolean correctAnswer) {
        Optional<Milionerzy> questionOpt = milionerzyRepository.findById(id);
        if (questionOpt.isEmpty()) {
            throw new NoSuchElementException("Nie znaleziono pytania o ID: " + id);
        }

        Milionerzy question = questionOpt.get();
        int currentLevel = question.getLevel();

        long questionsAtThisLevel = milionerzyRepository.countByLevel(currentLevel);

        if (questionsAtThisLevel <= 1) {
            return;
        }

        if (correctAnswer && currentLevel < 12) {
            question.setLevel(currentLevel + 1);
        } else if (!correctAnswer && currentLevel > 1) {
            question.setLevel(currentLevel - 1);
        }

        milionerzyRepository.save(question);
    }

}
