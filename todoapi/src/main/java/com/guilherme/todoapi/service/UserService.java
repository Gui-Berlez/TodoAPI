package com.guilherme.todoapi.service;

import com.guilherme.todoapi.model.User;
import com.guilherme.todoapi.model.UserDTO;
import com.guilherme.todoapi.model.exception.ResourceNotFoundException;
import com.guilherme.todoapi.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    // Injeção de dependencia
    public UserService(UserRepository userRepository,
                       BCryptPasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    //POST

    //O POST do USER pois já adicionamos o usuário no REGISTER em AuthController com o hasheamento correto


    //------------------------------------------------------------------------------------------------------------
    // GET
    public List<UserDTO> getUsers(){
        //converte User em UserDTO (esconde a senha)
        return userRepository.findAll()
                .stream() // transforma a lista em um fluxo processável
                .map(UserDTO::new) // para cada User, cria um UserDTO
                .toList(); // coleta tudo de volta numa lista
    }
    // GET por ID
    // Caso com tratamento de Exceções
    public UserDTO getUserById(Long id){
        //se encontrar → retorna Task
        //se NÃO → explode erro (exception)
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado!"));
        return new UserDTO(user);
    }

    //------------------------------------------------------------------------------------------------------------
    //DELETE
    public void deleteUser(Long id){
        userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usuário não existente!"));
        userRepository.deleteById(id);
    }

    //------------------------------------------------------------------------------------------------------------
    //PUT
    public UserDTO updateUser(Long id, User updateUser){

        User user = userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Usuário não encontrado!"));

        user.setUsername(updateUser.getUsername());
        // hasheia a noiva senha antes de salvar!
        user.setPassword(passwordEncoder.encode(updateUser.getPassword()));

        UserDTO userDTO = new UserDTO(userRepository.save(user));
        return userDTO;

    }




}



