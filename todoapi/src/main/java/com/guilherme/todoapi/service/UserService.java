package com.guilherme.todoapi.service;

import com.guilherme.todoapi.model.User;
import com.guilherme.todoapi.model.exception.ResourceNotFoundException;
import com.guilherme.todoapi.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    // Injeção de dependencia
    public UserService(UserRepository userRepository){ this.userRepository = userRepository; }

    //POST
    public User createUser(User user){
        return userRepository.save(user);
    }

    //------------------------------------------------------------------------------------------------------------
    // GET
    public List<User> getUsers(){
        return userRepository.findAll();
    }
    // GET por ID
    // Caso com tratamento de Exceções
    public User getUserById(Long id){
        //se encontrar → retorna Task
        //se NÃO → explode erro (exception)
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado!"));
    }

    //------------------------------------------------------------------------------------------------------------
    //DELETE
    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }

    //------------------------------------------------------------------------------------------------------------
    //PUT
    public User updateUser(Long id, User updateUser){

        User user = userRepository.findById(id).orElse(null);
        if(user != null){
            user.setUsername(updateUser.getUsername());
            user.setPassword(updateUser.getPassword());
            return userRepository.save(user);
        }

        return null;
    }




}



