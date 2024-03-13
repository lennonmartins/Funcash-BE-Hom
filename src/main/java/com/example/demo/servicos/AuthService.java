package com.example.demo.servicos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UsuarioResponseDto;
import com.example.demo.modelos.usuario.Usuario;
import com.example.demo.seguranca.TokenService;

@Service
public class AuthService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    TokenService tokenService;

    public UsuarioResponseDto login(String email, String senha) {

        var usernamePassword = new UsernamePasswordAuthenticationToken(email, senha);
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.gerarToken((Usuario) auth.getPrincipal());
        var userDetails = (Usuario) auth.getPrincipal();

        var idDaPessoa = obterIdDaPessoaValida(userDetails);

        var usuarioResponse = new UsuarioResponseDto(userDetails.getId(),
                idDaPessoa,
                userDetails.getRole(),
                token);

        return usuarioResponse;
    }

    private Long obterIdDaPessoaValida(Usuario userDetails) {
        if (userDetails.getCrianca() == null)
            return userDetails.getResponsavel().getId();
        else {
            return userDetails.getCrianca().getId();
        }
    }
}
