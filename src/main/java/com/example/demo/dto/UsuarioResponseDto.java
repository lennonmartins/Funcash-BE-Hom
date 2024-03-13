package com.example.demo.dto;

import com.example.demo.modelos.usuario.UserRole;

public record UsuarioResponseDto(Long idDoUsuario, Long idDaPessoa, UserRole role, String token) {

}
