package com.guilherme.todoapi.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component // virou um Bean do Spring — pode ser injetado em outras classes
public class JwtUtil {

    private final Key key;

    // @Value lê do application.properties
    // Se a propriedade não existir, usa o valor padrão após o ':'
    public JwtUtil(@Value("${jwt.secret:7#kP2@mXqL9$vNzR4&wYcF6!sTbE1^uA-123456}") String secret) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    // Gera o token com username, data de criação e expiração
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 hora
                .signWith(key)
                .compact();
    }

    // Extrai o username de dentro do token
    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    // Valida se o token é legítimo e não expirou
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token); // lança exceção se inválido ou expirado
            return true;
        } catch (JwtException e) {
            return false;
        }
    }
}