package com.guilherme.todoapi;

import com.guilherme.todoapi.model.Task;
import com.guilherme.todoapi.model.exception.ResourceNotFoundException;
import com.guilherme.todoapi.repository.TaskRepository;
import com.guilherme.todoapi.service.TaskService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) // ativa o Mockito
class TaskServiceTest {

    @Mock
    TaskRepository taskRepository; // dublê do repositório

    @InjectMocks
    TaskService taskService; // injeta o Mock acima no service

    @Test
    void RetornarListaDeTasks(){
        // ARRANGE — prepara o cenário
        Task task = new Task();
        task.setTitle("Estudar Spring");
        task.setDescription("Aprender testes");
        when(taskRepository.findAll()).thenReturn(List.of(task));

        // ACT — executa o metodo
        List<Task> resultado = taskService.getTasks();

        // ASSERT — verifica o resultado
        assertEquals(1, resultado.size());
        assertEquals("Estudar Spring", resultado.get(0).getTitle());

    }

    @Test
    void deveLancarExcecaoQuandoTaskNaoEncontrada() {
        // ARRANGE
        when(taskRepository.findById(99L)).thenReturn(Optional.empty());

        // ACT + ASSERT
        assertThrows(ResourceNotFoundException.class, () -> {
            taskService.getTaskById(99L);
        });
    }

    @Test
    void deveDeletarTaskExistente() {
        // ARRANGE
        Task task = new Task();
        task.setTitle("Task para deletar");
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));

        // ACT
        taskService.deleteTask(1L);

        // ASSERT — verifica que o deleteById foi chamado
        verify(taskRepository, times(1)).deleteById(1L);
    }

}
