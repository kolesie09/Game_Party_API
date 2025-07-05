package com.codewithproject.gameparty.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class FamiliadaAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;   // tekst odpowiedzi
    private Integer point;  // liczba punktów jako tekst? (może lepiej Integer)

    @ManyToOne
    @JoinColumn(name = "familiada_id")
    @JsonBackReference
    private Familiada familiada;
}
