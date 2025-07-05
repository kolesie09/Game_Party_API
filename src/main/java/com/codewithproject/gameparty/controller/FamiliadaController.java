package com.codewithproject.gameparty.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codewithproject.gameparty.entity.Familiada;
import com.codewithproject.gameparty.service.FamiliadaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/familiada")
@RequiredArgsConstructor
@CrossOrigin("*")
public class FamiliadaController {

    private final FamiliadaService familiadaService;

    @GetMapping("/random")
    public ResponseEntity<Familiada> getRandomQuestionWithAnswers() {
        Familiada question = familiadaService.getRandomQuestion();
        return ResponseEntity.ok(question);
    }

}
