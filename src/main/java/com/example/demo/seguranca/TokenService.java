package com.example.demo.seguranca;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Service
public class TokenService {
    private static final String SECRET_KEY = "your_secret_key";
    private static final long VALIDITY_DURATION_MS = 3600000; // 1 hour

    private final String issuer = "funcash-api";

    public String generateToken(String username) {
        Date now = new Date();

        Algorithm algoritmo = Algorithm.HMAC256(SECRET_KEY);
        String token = JWT.create()
                .withIssuer(issuer)
                .withSubject(username)
                .withExpiresAt(getExpiracao())
                .sign(algoritmo);
        return token;
    }

    public String isValidToken(String token) {
        try {
            Algorithm algoritmo = Algorithm.HMAC256(SECRET_KEY);
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

    public String getUsernameFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }
}