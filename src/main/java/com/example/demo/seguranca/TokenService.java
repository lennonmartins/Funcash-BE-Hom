package com.example.demo.seguranca;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.demo.modelos.usuario.Usuario;

@Service
public class TokenService {

    @Value("${funcash.security.token.secret}")
    private String segredo;

    private final String issuer = "funscash-api";

    public String gerarToken(Usuario usuario) {
        try {
            Algorithm algoritmo = Algorithm.HMAC256(segredo);
            String token = JWT.create()
                    .withIssuer(issuer)
                    .withSubject(usuario.getEmail())
                    .withExpiresAt(getExpiracao())
                    .sign(algoritmo);
            return token;
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro ao gerar token.", exception);
        }
    }

    public String validarToken(String token) {
        try {
            Algorithm algoritmo = Algorithm.HMAC256(segredo);
            return JWT.require(algoritmo)
                    .withIssuer(issuer)
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            return "";
        }
    }

    public Instant getExpiracao() {
        return LocalDateTime.now().plusMonths(6).toInstant(ZoneOffset.of("-03:00"));
    }
}
