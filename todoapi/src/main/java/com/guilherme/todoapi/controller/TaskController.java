package com.guilherme.todoapi.controller;

import com.guilherme.todoapi.model.Task;
import com.guilherme.todoapi.repository.TaskRepository;
import com.guilherme.todoapi.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TaskController {

    // Com o @Autowired é como se o Spring falasse: "vou pegar o Bean TaskService e colocar aqui". Por isso que chamamos as classes de TaskService mesmo sem instâncias.
    @Autowired //Esse comando me dá uma instancia do atributo abaixo, no caso da classe TaskService;
    private TaskService taskService;

// POST
    @PostMapping("/tasks")
    public Task createTask(@Valid @RequestBody Task task){
        // Spring verifica as anotações (@NotBlank, @Size...) ANTES de entrar no metodo
        return taskService.createTask(task);
    }
//------------------------------------------------------------------------------------------------------------
// GET
    @GetMapping("/tasks")
    public List<Task> getTasks(){

        return taskService.getTasks();
    }

    @GetMapping("/tasks/{id}")
    public Task getTaskById(@PathVariable Long id){
        return taskService.getTaskById(id);
    }

//------------------------------------------------------------------------------------------------------------
// DELETE
    @DeleteMapping("/tasks/{id}")
    public void deleteTask(@PathVariable Long id){
        taskService.deleteTask(id);
    }
//------------------------------------------------------------------------------------------------------------
// PUT
    @PutMapping("/tasks/{id}")
    public Task updateTask(@PathVariable Long id, @Valid @RequestBody Task task){
        return taskService.updateTask(id, task);
    }

}
