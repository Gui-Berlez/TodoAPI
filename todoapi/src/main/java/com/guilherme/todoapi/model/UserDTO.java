package com.guilherme.todoapi.model;

public class UserDTO {

    private Long id;
    private String username;

    // Contructor que recebe um User e extrai só o que interessao aoo cliente


    public UserDTO(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
    }

    public Long getId() {
        return id;
    }
    public String getUsername() {
        return username;
    }
}
