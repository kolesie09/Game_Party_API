package com.codewithproject.gameparty.service;

import org.springframework.stereotype.Service;

import com.codewithproject.gameparty.entity.Familiada;
import com.codewithproject.gameparty.repository.FamiliadaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FamiliadaService {

    private final FamiliadaRepository familiadaRepository;

    public Familiada getRandomQuestion() {
        return familiadaRepository.findRandomQuestion();
    }

}
