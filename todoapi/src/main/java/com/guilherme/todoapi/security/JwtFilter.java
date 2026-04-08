package com.guilherme.todoapi.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JwtFilter extends OncePerRequestFilter {
    // OncePerRequestFilter = garante que esse filtro roda UMA vez por requisição

    private final JwtUtil jwtUtil;

    public JwtFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        // 1. Pega o header "Authorization" da requisição
        String authHeader = request.getHeader("Authorization");

        // 2. Verifica se o header existe e começa com "Bearer "
        // "Bearer xxxxxxxxxxx" é o padrão para tokens JWT
        if (authHeader != null && authHeader.startsWith("Bearer ")) {


            // 3. Remove o "Bearer " e fica só com o token
            String token = authHeader.substring(7);

            // 4. Valida o token
            if (jwtUtil.validateToken(token)) {

                // 5. Extrai o username do token
                String username = jwtUtil.extractUsername(token);

                // 6. Cria um objeto de autenticação e registra no Spring Security
                // Isso diz pro Spring: "esse usuário está autenticado"
                UsernamePasswordAuthenticationToken auth =
                        new UsernamePasswordAuthenticationToken(username, null, List.of());

                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }

    // 7. Continua a requisição (passa para o próximo filtro ou controller)
    filterChain.doFilter(request, response);

    }
}
