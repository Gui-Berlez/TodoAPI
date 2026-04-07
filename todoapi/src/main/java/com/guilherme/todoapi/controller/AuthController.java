package com.guilherme.todoapi.controller;

import com.guilherme.todoapi.model.User;
import com.guilherme.todoapi.repository.UserRepository;
import com.guilherme.todoapi.security.JwtUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository userRepository;

    public AuthController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @PostMapping("/login")
    public String login(@RequestBody User user) {


        //FAZER TRATAMENTO DAS EXCEÇÕES DE USER N ENONTRADO E SENHA INVALIDA!!!!!!!!!!!!!!

        User dbUser = userRepository.findByUsername(user.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (!dbUser.getPassword().equals(user.getPassword())) {
            throw new RuntimeException("Senha inválida");
        }

        return JwtUtil.generateToken(user.getUsername());


    }
}
