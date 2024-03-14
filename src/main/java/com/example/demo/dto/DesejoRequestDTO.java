package com.example.demo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DesejoRequestDTO {
    private String nome;
    private String descricao;
    private double valor;
    private Long idDaCrianca;

    public DesejoRequestDTO(String nome, String descricao, double valor, Long idDaCrianca) {
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
        this.idDaCrianca = idDaCrianca;
    }
}
