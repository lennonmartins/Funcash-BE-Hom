package com.example.demo.servicos;

import org.apache.commons.codec.EncoderException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UsuarioResponseDto;
import com.example.demo.modelos.usuario.Usuario;
import com.example.demo.repositorios.UsuarioRepository;
import com.example.demo.seguranca.TokenService;

@Service
public class AuthService {

    private final TokenService tokenService;
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);

    @Autowired
    public AuthService(TokenService tokenService, UsuarioRepository usuarioRepository,
            PasswordEncoder passwordEncoder) {
        this.tokenService = tokenService;
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UsuarioResponseDto login(String email, String senha) throws EncoderException {
        // Encontrar usuário por email
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com o email: " + email));

        // Verificar senha
        if (!passwordEncoder.matches(senha, usuario.getSenha())) {
            throw new EncoderException("Senha incorreta");
        }

        // Gerar token de autenticação
        String token = tokenService.gerarToken(usuario);

        // Criar e retornar resposta
        return new UsuarioResponseDto(usuario.getId(), obterIdDaPessoaValida(usuario), usuario.getRole(), token);
    }

    private Long obterIdDaPessoaValida(Usuario usuario) {
        if (usuario.getCrianca() == null) {
            return usuario.getResponsavel().getId();
        } else {
            return usuario.getCrianca().getId();
        }
    }
}
