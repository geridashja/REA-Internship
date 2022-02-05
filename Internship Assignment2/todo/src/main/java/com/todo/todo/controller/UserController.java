package com.todo.todo.controller;


import com.todo.todo.entity.Todo;
import com.todo.todo.entity.User;
import com.todo.todo.repository.TodoRepository;
import com.todo.todo.repository.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/users")
public class UserController {

    //connecting to repos
    private UserRepository userRepo;
    private TodoRepository todoRepo;

    public UserController(UserRepository userRepo, TodoRepository todoRepo) {
        this.userRepo = userRepo;
        this.todoRepo = todoRepo;
    }

    @GetMapping("/{userId}")
    public User getUserbyId(@PathVariable Long userId){
        return userRepo.findById(userId).orElseThrow(
                () -> new NoSuchElementException());
    }
}
