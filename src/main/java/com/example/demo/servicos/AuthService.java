package com.example.demo.servicos;

import org.apache.commons.codec.EncoderException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UsuarioResponseDto;
import com.example.demo.modelos.usuario.Usuario;
import com.example.demo.repositorios.UsuarioRepository;
import com.example.demo.seguranca.SecurityConfig;
import com.example.demo.seguranca.TokenService;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {

    private final TokenService tokenService;
    private final UsuarioRepository usuarioRepository;
    private final SecurityConfig passwordEncoder;
    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);

    @Autowired
    public AuthService(TokenService tokenService, UsuarioRepository usuarioRepository) {
        this.tokenService = tokenService;
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = new SecurityConfig();
    }

    //@Transactional
    public UsuarioResponseDto login(String email, String senha) throws EncoderException {
        var usuario = usuarioRepository.findByEmail(email).orElseThrow(() -> new EncoderException("Usuário não encontrado"));

        if (!passwordEncoder.matches(senha, usuario.getSenha())) {
            throw new EncoderException("Senha incorreta");
        }

        String token = tokenService.generateToken(usuario.getUsername());

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
