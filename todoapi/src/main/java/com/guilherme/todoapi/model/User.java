package com.guilherme.todoapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class User {

    //-----------------------------------------------------------------------------------------------------------------------------------------
    // Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome de usuário é obrigatório!")
    @Size(min = 3, message = "O nome de usuário tem q ter pelo menos 3 caracteres")
    private String username;
    @NotBlank(message = "A senha é obrigatória!")
    @Size(min = 6, message = "A senha precisa ter pelo menos 6 caracteres")
    private String password;

    //-----------------------------------------------------------------------------------------------------------------------------------------
    // Constructor
    public User() {}

    public User(User user) {
    }

    //-----------------------------------------------------------------------------------------------------------------------------------------
    // Getters and Setters
    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
