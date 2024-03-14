package com.example.demo.dto;

import com.example.demo.modelos.Desejo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DesejoResponseDTO {
    private Long id;
    private String nome;
    private String descricao;
    private double valor;

    public DesejoResponseDTO(Desejo desejo){
        this.id = desejo.getId();
        this.nome = desejo.getTitulo();
        this.descricao = desejo.getDescricao();
        this.valor = desejo.getValor();
    }
}
