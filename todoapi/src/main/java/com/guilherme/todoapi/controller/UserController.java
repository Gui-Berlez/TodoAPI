package com.guilherme.todoapi.controller;

import com.guilherme.todoapi.model.User;
import com.guilherme.todoapi.repository.UserRepository;
import com.guilherme.todoapi.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    // POST
    //  Comentei o POST do USER pois já adicionamos o usuário no REGISTER em AuthController com o hasheamento correto

    //    @PostMapping("/users")
    //    public User creataUser(@Valid @RequestBody User user){
    //        return userService.createUser(user);
    //    }


    //------------------------------------------------------------------------------------------------------------
    // GET
    @GetMapping("/users")
    public List<User> getUser(){
        return userService.getUsers();
    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable Long id){
       return userService.getUserById(id);
    }

    //------------------------------------------------------------------------------------------------------------
    // DELETE
    @DeleteMapping("/users/{id})")
    public void deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
    }


    //------------------------------------------------------------------------------------------------------------
    // PUT
    @PutMapping("/users/{id}")
    public User updateUser(@PathVariable Long id, @Valid @RequestBody User user){
        return userService.updateUser(id, user);
    }


}
