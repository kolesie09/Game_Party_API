package com.codewithproject.gameparty.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class QuestionDto {

    private Long id;
    private String name;
    private Long correctAnswer;
    private List<AnswerDto> answers;
}
