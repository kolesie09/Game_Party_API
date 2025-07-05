package com.codewithproject.gameparty.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codewithproject.gameparty.entity.Familiada;
import com.codewithproject.gameparty.entity.FamiliadaAnswer;
import com.codewithproject.gameparty.service.FamiliadaAnswerService;
import com.codewithproject.gameparty.service.FamiliadaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/familiada")
@RequiredArgsConstructor
@CrossOrigin("*")
public class FamiliadaController {

    private final FamiliadaService familiadaService;
    private final FamiliadaAnswerService familiadaAnswerService;

    @GetMapping("/random")
    public ResponseEntity<Familiada> getRandomQuestionWithAnswers() {
        Familiada question = familiadaService.getRandomQuestion();
        return ResponseEntity.ok(question);
    }

    @PostMapping("/{id}/answers")
    public ResponseEntity<?> postFamiliadaAnswer(
            @PathVariable Long id,
            @RequestBody FamiliadaAnswer familiadaAnswer
    ) {
        FamiliadaAnswer result = familiadaAnswerService.postFamiliadaAnswer(id, familiadaAnswer);
        if (result == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping("/question")
    public ResponseEntity<Familiada> postFamiliada(@RequestBody Familiada familiada) {
        if (familiada.getAnswers() != null) {
            for (FamiliadaAnswer answer : familiada.getAnswers()) {
                answer.setFamiliada(familiada);
            }
        }
        Familiada saved = familiadaService.postFamiliada(familiada);
        return ResponseEntity.ok(saved);
    }

}
