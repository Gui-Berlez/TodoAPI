package com.guilherme.todoapi.service;

import com.guilherme.todoapi.model.Task;
import com.guilherme.todoapi.model.exception.ResourceNotFoundException;
import com.guilherme.todoapi.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

//Sem o @Service:
//TaskService = classe comum (Java puro)

//Com o @Service:
//TaskService = objeto controlado pelo Spring (Bean)

@Service // Significa que essa classe está sendo gerenciada pelo Spring
public class TaskService {

    private final TaskRepository taskRepository;

     // Injeção de dependencia
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    //POST
    public Task createTask(Task task){

        return taskRepository.save(task); //Esse comando já salva a tarefa no banco de dados
    }
//------------------------------------------------------------------------------------------------------------

// GET
    public List<Task> getTasks(){

        return taskRepository.findAll(); //Esse comando retorna toda a tabela, por isso em formato de lista
    }
    // GET por ID
    // Caso com tratamento de Exceções
    public Task getTaskById(Long id){
        //se encontrar → retorna Task
        //se NÃO → explode erro (exception)
        return taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Tarefa não encontrada!"));
    }
//------------------------------------------------------------------------------------------------------------

    //DELETE
    public void deleteTask(Long id){

        taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Tarefa não existente!"));

        taskRepository.deleteById(id); //Esse comando deleta a tarefa peo ID, que estará na URL


        //Praticamente essa linha abaixo diz:
        //"Remove da lista a task que tiver o id igual ao informado"
        // tasks.removeIf(task -> task.getId().equals(id));
    }
//------------------------------------------------------------------------------------------------------------

    //PUT
    public Task updateTask(Long id, Task updateTask){

        Task task = taskRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Tarefa não encontrada!"));// Cria um Objeto Task que recebe a função do Repository que irá procurar o ID no banco,
                                                                    // caso ele não ache, retorna null

            task.setTitle(updateTask.getTitle());
            task.setDescription(updateTask.getDescription());
            task.setCompleted(updateTask.isCompleted());
            return taskRepository.save(task);

    }

}
