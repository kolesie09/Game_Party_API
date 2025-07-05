package com.codewithproject.gameparty.service;

import org.springframework.stereotype.Service;

import com.codewithproject.gameparty.repository.FamiliadaAnswerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FamiliadaAnswerService {

    private final FamiliadaAnswerRepository familiadaAnswerRepository;

}
