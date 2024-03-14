package com.example.demo.dto;

import java.time.LocalDate;

import com.example.demo.modelos.Crianca;
import com.example.demo.modelos.Genero;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CriancaResponseDTO {
    private Long id;

    private LocalDate dataDeNascimento;

    private String email;

    private String senha;

    private double saldo;

    private String nome;

    private String apelido;

    private Genero genero;

    private String foto;

    public CriancaResponseDTO(Crianca crianca) {
        this.id = crianca.getId();
        this.dataDeNascimento = crianca.getDataDeNascimento();
        this.email = crianca.getUsuario().getEmail();
        this.senha = crianca.getUsuario().getSenha();
        this.saldo = crianca.getSaldo();
        this.nome = crianca.getNome();
        this.apelido = crianca.getApelido();
        this.genero = crianca.getGenero();
        this.foto = crianca.getFoto();
    }
}
