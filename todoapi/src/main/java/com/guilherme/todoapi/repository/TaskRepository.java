package com.guilherme.todoapi.repository;

import com.guilherme.todoapi.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {

//    Você acabou de ganhar automaticamente:
//    save(task)
//    findAll()
//    deleteById(id)
//    findById(id)
//    sem escrever implementação
}


