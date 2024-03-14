package com.example.demo.dto;

import java.time.LocalDate;

import com.example.demo.modelos.Genero;
import com.example.demo.modelos.Responsavel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponsavelResponseDTO {
    private Long id;
    private String nome;
    private String email;
    private String cpf;
    private LocalDate dataDeNascimentoResponsavel;
    private Genero genero;
    private String senha;
    private String foto;

    public ResponsavelResponseDTO(Responsavel responsavel) {
        this.id = responsavel.getId();
        this.nome = responsavel.getNome();
        this.email = responsavel.getUsuario().getEmail();
        this.cpf = responsavel.getCpf();
        this.dataDeNascimentoResponsavel = responsavel.getDataDeNascimento();
        this.genero = responsavel.getGenero();
        this.senha = responsavel.getUsuario().getSenha();
        this.foto = responsavel.getFoto();
    }

}
