package com.guilherme.todoapi.repository;

import com.guilherme.todoapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // Optional é um "envelope" que pode ou não conter um valor. É a forma do Java dizer: "pode ser que esse usuário não exista"
    Optional<User> findByUsername(String username);// Precisamos buscar o usuário no login
}
