package com.codewithproject.gameparty.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.codewithproject.gameparty.entity.Familiada;
import com.codewithproject.gameparty.entity.FamiliadaAnswer;
import com.codewithproject.gameparty.repository.FamiliadaAnswerRepository;
import com.codewithproject.gameparty.repository.FamiliadaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FamiliadaAnswerService {

    private final FamiliadaAnswerRepository familiadaAnswerRepository;
    private final FamiliadaRepository familiadaRepository;

    public FamiliadaAnswer postFamiliadaAnswer(Long familiadaId, FamiliadaAnswer familiadaAnswer) {
        Optional<Familiada> familiadaOpt = familiadaRepository.findById(familiadaId);
        if (familiadaOpt.isEmpty()) {
            return null; // brak pytania - zwróć null
        }

        Familiada familiada = familiadaOpt.get();

        // Szukamy czy istnieje odpowiedź o takim samym tekście i dla tego pytania
        Optional<FamiliadaAnswer> existingAnswerOpt = familiadaAnswerRepository
                .findByFamiliadaAndName(familiada, familiadaAnswer.getName());

        if (existingAnswerOpt.isPresent()) {
            FamiliadaAnswer existingAnswer = existingAnswerOpt.get();
            // zwiększamy punkty o 1
            existingAnswer.setPoint(existingAnswer.getPoint() + 1);
            return familiadaAnswerRepository.save(existingAnswer);
        } else {
            // ustawiamy powiązanie z pytaniem i punkty
            familiadaAnswer.setFamiliada(familiada);
            familiadaAnswer.setPoint(1);
            return familiadaAnswerRepository.save(familiadaAnswer);
        }
    }

}
