package com.guilherme.todoapi.service;

import com.guilherme.todoapi.model.User;
import com.guilherme.todoapi.model.exception.ResourceNotFoundException;
import com.guilherme.todoapi.repository.UserRepository;
import com.guilherme.todoapi.security.JwtUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final BCryptPasswordEncoder passwordEncoder;

    // Spring injeta tudo pelo construtor
    public AuthService(UserRepository userRepository,
                          JwtUtil jwtUtil,
                          BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    // Cadastro — hasheia a senha antes de salvar
    @PostMapping("/register")
    public String register(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "Usuário cadastrado com sucesso!";
    }

    // Login — compara a senha com o hash do banco
    @PostMapping("/login")
    public String login(@RequestBody User user) {
        User dbUser = userRepository.findByUsername(user.getUsername())
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));

        // .matches() hasheia a senha digitada e compara com o hash salvo
        if (!passwordEncoder.matches(user.getPassword(), dbUser.getPassword())) {
            throw new RuntimeException("Senha inválida");
        }

        return jwtUtil.generateToken(user.getUsername());
    }

}
