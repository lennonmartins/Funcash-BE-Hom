package com.example.demo.dto;

import com.example.demo.modelos.Genero;
import com.example.demo.modelos.usuario.UserRole;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
