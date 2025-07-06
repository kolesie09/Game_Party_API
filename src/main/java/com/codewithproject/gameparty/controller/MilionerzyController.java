package com.codewithproject.gameparty.controller;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codewithproject.gameparty.dto.QuestionDto;
import com.codewithproject.gameparty.dto.QuestionDtoLevel;
import com.codewithproject.gameparty.service.MilionerzyAnswerService;
import com.codewithproject.gameparty.service.MilionerzyService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/milionerzy")
@RequiredArgsConstructor
@CrossOrigin("*")
public class MilionerzyController {

    private final MilionerzyService milionerzyService;
    private final MilionerzyAnswerService milionerzyAnswerService;

    // Pobierz losowe pytanie z danego poziomu
    @GetMapping("/question")
    public ResponseEntity<QuestionDto> getRandomQuestion(@RequestParam int level) {
        Optional<QuestionDto> questionOpt = milionerzyService.getRandomQuestionByLevel(level);
        return questionOpt
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/question")
    public ResponseEntity<QuestionDtoLevel> postNewQuestion(@RequestBody QuestionDtoLevel questionDto) {
        // Zapisz pytanie i odpowiedzi przy pomocy serwisu
        QuestionDtoLevel savedQuestion = milionerzyService.saveQuestionWithAnswers(questionDto);
        return ResponseEntity.ok(savedQuestion);
    }

    @PutMapping("/question/{id}/level")
    public ResponseEntity<?> updateQuestionLevel(
            @PathVariable Long id,
            @RequestParam boolean correctAnswer) {

        try {
            milionerzyService.updateQuestionLevel(id, correctAnswer);
            return ResponseEntity.ok().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
