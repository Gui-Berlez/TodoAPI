package com.guilherme.todoapi.controller;

import com.guilherme.todoapi.model.User;
import com.guilherme.todoapi.repository.UserRepository;
import com.guilherme.todoapi.security.JwtUtil;
import com.guilherme.todoapi.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    // Spring injeta tudo pelo construtor
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // Cadastro — hasheia a senha antes de salvar
    @PostMapping("/register")
    public String register(@Valid @RequestBody User user) {
        return authService.register(user);
    }

    // Login — compara a senha com o hash do banco
    @PostMapping("/login")
    public String login(@Valid @RequestBody User user) {
        return authService.login(user);
    }
}