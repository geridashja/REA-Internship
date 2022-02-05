package com.todo.todo.controller;


import com.todo.todo.repository.TodoRepository;
import com.todo.todo.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
//@RequestMapping("/users")
public class UserController {

    //connecting to repos
    private UserRepository userRepo;
    private TodoRepository todoRepo;

    public UserController(UserRepository userRepo, TodoRepository todoRepo) {
        this.userRepo = userRepo;
        this.todoRepo = todoRepo;
    }

    @GetMapping("/home")
    public String rethome(){
        return "HELLO";
    }
//    public User getUserbyId(@PathVariable Long userId){
//        return userRepo.findById(userId).orElseThrow(
//                () -> new NoSuchElementException());
//    }

//    @PostMapping
//    public User addUser(@RequestBody AddUser userRequest){
//        User user = new User();
//        user.setUsername(userRequest.getUsername());
//        user.setPassword(userRequest.getPassword());
//        return userRepo.save(user);
//    }
}
