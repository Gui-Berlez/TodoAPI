package com.guilherme.todoapi;

import com.guilherme.todoapi.model.User;
import com.guilherme.todoapi.repository.UserRepository;
import com.guilherme.todoapi.security.JwtUtil;
import com.guilherme.todoapi.service.AuthService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @Mock
    UserRepository userRepository;

    @Mock
    BCryptPasswordEncoder passwordEncoder;

    @Mock
    JwtUtil jwtUtil;

    @InjectMocks
    AuthService authService;

    @Test
    void deveRealizarLoginComSucesso() {
        // ARRANGE
        User userRequest = new User();
        userRequest.setUsername("guilherme");
        userRequest.setPassword("123456");

        User userDB = new User();
        userDB.setUsername("guilherme");
        userDB.setPassword("$2a$hash");

        when(userRepository.findByUsername("guilherme"))
                .thenReturn(Optional.of(userDB));
        when(passwordEncoder.matches("123456", "$2a$hash"))
                .thenReturn(true);
        when(jwtUtil.generateToken("guilherme"))
                .thenReturn("token-fake");

        // ACT
        String token = authService.login(userRequest);

        // ASSERT
        assertEquals("token-fake", token);
    }

    @Test
    void deveLancarExcecaoQuandoSenhaInvalida() {
        // ARRANGE
        User userRequest = new User();
        userRequest.setUsername("guilherme");
        userRequest.setPassword("senhaErrada");

        User userDB = new User();
        userDB.setUsername("guilherme");
        userDB.setPassword("$2a$hash");

        when(userRepository.findByUsername("guilherme"))
                .thenReturn(Optional.of(userDB));
        when(passwordEncoder.matches("senhaErrada", "$2a$hash"))
                .thenReturn(false);

        // ACT + ASSERT
        assertThrows(RuntimeException.class, () -> {
            authService.login(userRequest);
        });
    }

    @Test
    void deveCadastrarUsuarioComSucesso() {
        // ARRANGE
        User user = new User();
        user.setUsername("novo");
        user.setPassword("123456");

        when(passwordEncoder.encode("123456")).thenReturn("$2a$hash");
        when(userRepository.save(any(User.class))).thenReturn(user);

        // ACT
        String resultado = authService.register(user);

        // ASSERT
        assertEquals("Usuário cadastrado com sucesso!", resultado);
        verify(passwordEncoder, times(1)).encode("123456");
        verify(userRepository, times(1)).save(user);
    }
}
