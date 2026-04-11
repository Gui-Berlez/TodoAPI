package com.guilherme.todoapi.controller;

import com.guilherme.todoapi.model.Task;
import com.guilherme.todoapi.repository.TaskRepository;
import com.guilherme.todoapi.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    // Com o @Autowired é como se o Spring falasse: "vou pegar o Bean TaskService e colocar aqui". Por isso que chamamos as classes de TaskService mesmo sem instâncias.
    // @Autowired - Esse comando me dá uma instancia do atributo abaixo, no caso da classe TaskService;
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

// POST
    @PostMapping
    public ResponseEntity<Task> createTask(@Valid @RequestBody Task task){
        // Spring verifica as anotações (@NotBlank, @Size...) ANTES de entrar no metodo
        return ResponseEntity.status(201).body(taskService.createTask(task));
    }
//------------------------------------------------------------------------------------------------------------
// GET
    @GetMapping
    public List<Task> getTasks(){

        return taskService.getTasks();
    }

    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable Long id){
        return taskService.getTaskById(id);
    }

//------------------------------------------------------------------------------------------------------------
// DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();// retorna 204, mostrando que está tud certo, mas que não tem nenhum retorno}
    }

//------------------------------------------------------------------------------------------------------------
// PUT
    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id, @Valid @RequestBody Task task){
        return taskService.updateTask(id, task);
    }

}
