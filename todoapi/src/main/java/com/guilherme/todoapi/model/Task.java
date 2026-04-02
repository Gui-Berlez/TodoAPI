package com.guilherme.todoapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Task {


    //-----------------------------------------------------------------------------------------------------------------------------------------
    // Atributos

    @Id //Identifica qual é a Primary Key da tabela(A que fica logo a baixo desse comando)
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Realiza o AUTO_INCREMENT no banco, adicionando mais um no valor do ID na tabela
    // A Primary Key é sempre o atributo a que está abaixo dessas linhas acima!!
    private Long id;

    //@NotBlank = Não aceita null, "" e espaços
    @NotBlank(message = "O título precisa ser prenchido!")
    //@Size  = Define o tamanho minimo/máximo
    @Size(min = 3, message = "O título deve ter pelo menos 3 caracteres")
    private String title;

    @NotBlank(message = "A descrição precisa ser preenchida!")
    @Size(min = 5, message = "A descrição deve ter pelo menos 5 caracteres")
    private String description;
    private boolean completed;
    //-----------------------------------------------------------------------------------------------------------------------------------------
    // Constructor

    public Task() {
    }

    //-----------------------------------------------------------------------------------------------------------------------------------------
    // Getters and Setters

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
