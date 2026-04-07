package com.guilherme.todoapi.security;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

// Lógica de segurança (token)
public class JwtUtil {

        private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

        public static String generateToken(String username) {
            return Jwts.builder()
                    .setSubject(username)        // quem é o dono do token
                    .setIssuedAt(new Date())     // quando foi criado
                    .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))  // expira em 1 hora
                    .signWith(key)               // assina com a chave secreta
                    .compact();                  // gera a String final (o token gigante)
        }

        public static String extractUsername(String token) {
            return Jwts.parserBuilder()
                    .setSigningKey(key)    // usa a mesma chave pra validar
                    .build()
                    .parseClaimsJws(token) // lê e valida o token
                    .getBody()             // pega o payload
                    .getSubject();         // pega o username
        }

}
