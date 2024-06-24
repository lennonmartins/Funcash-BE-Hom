package com.example.demo.dto;

import com.example.demo.modelos.Genero;
import com.example.demo.modelos.usuario.UserRole;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ResponsavelRequestDTO {
    private String nome;
    private String email;
    private String cpf;
    private String dataDeNascimentoResponsavel;
    private Genero genero;
    private String senha;
    private String foto;
    private UserRole role;
}
