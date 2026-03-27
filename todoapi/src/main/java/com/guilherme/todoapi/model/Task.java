package com.guilherme.todoapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.EntityGraph;

@Entity
public class Task {

    @Id //Identifica qual é a Primary Key da tabela(A que fica logo a baixo desse comando)
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Realiza o AUTO_INCREMENT no banco, adicionando mais um no valor do ID na tabela
    // A Primary Key é sempre o atributo a que está abaixo dessas linhas acima!!
    private Long id;
    private String title;
    private String description;
    private boolean completed;


    public Long getId() { return id; }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
