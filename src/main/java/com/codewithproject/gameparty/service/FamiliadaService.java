package com.codewithproject.gameparty.service;

import org.springframework.stereotype.Service;

import com.codewithproject.gameparty.entity.Familiada;
import com.codewithproject.gameparty.entity.FamiliadaAnswer;
import com.codewithproject.gameparty.repository.FamiliadaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FamiliadaService {

    private final FamiliadaRepository familiadaRepository;

    public Familiada getRandomQuestion() {
        return familiadaRepository.findRandomQuestion();
    }

    public Familiada postFamiliada(Familiada familiada) {
        // ustawia relację z pytaniem wewnątrz każdej odpowiedzi (jeśli nie zrobiłeś w setAnswers)
        for (FamiliadaAnswer answer : familiada.getAnswers()) {
            answer.setFamiliada(familiada);
            if (answer.getPoint() == 0) {
                answer.setPoint(1); // jeśli nie ustawiono punktów, nadaj domyślnie 1
            }
        }

        return familiadaRepository.save(familiada);
    }

}
