package com.example.demo.controladores;

import com.example.demo.modelos.Genero;
import com.example.demo.modelos.usuario.UserRole;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CriancaRequestDTO {
    private String dataDeNascimento;
    private String email;
    private String senha;
    private double saldo;
    private String nome;
    private String apelido;
    private Genero genero;
    private String foto;
    private Long idDoResponsavel;
    private UserRole role;
}
