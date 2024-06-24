package com.example.demo.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.example.demo.modelos.StatusDaTarefa;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class TarefaResponseDTO {
    private Long id;
    private LocalDateTime horaLimite;
    private LocalDate dataLimite;
    private LocalDateTime dataDeCriacao;
    private double valor;
    private String nome;
    private String descricao;
    private StatusDaTarefa status;
    private long idDaCrianca;
}
