package com.codewithproject.gameparty.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionDtoLevel {

    private Long id;
    private String name;           // <- tutaj musi być
    private Integer level;
    private String correctAnswer;    // ID poprawnej odpowiedzi
    private List<AnswerDto> answers;
}
