package com.guilherme.todoapi.controller;

import com.guilherme.todoapi.model.User;
import com.guilherme.todoapi.repository.UserRepository;
import com.guilherme.todoapi.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users") // Com isso, não precisa repetir o /users em cada Mapping dos repositórios
public class UserController {

    private final UserService userService;

    // construtor — Spring injeta automaticamente, sem @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // POST
    //  O POST do USER pois já adicionamos o usuário no REGISTER em AuthController com o hasheamento correto


    //------------------------------------------------------------------------------------------------------------
    // GET
    @GetMapping
    public List<User> getUser(){
        return userService.getUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id){
       return userService.getUserById(id);
    }

    //------------------------------------------------------------------------------------------------------------
    // DELETE
    @DeleteMapping("/{id})")
    public void deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
    }


    //------------------------------------------------------------------------------------------------------------
    // PUT
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @Valid @RequestBody User user){
        return userService.updateUser(id, user);
    }


}
