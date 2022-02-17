package com.todo.todo.controller;

import com.todo.todo.entity.todouser;
import com.todo.todo.repository.TodoRepository;
import com.todo.todo.repository.todouserrepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;


@Controller
public class MainController {
    @Autowired
    todouserrepo userRepository;

    @Autowired
    private TodoRepository Todorepository;

    //postmap testing purposes
    @GetMapping("/login")
    public String login(@ModelAttribute("todouser") todouser user) {

        return "login";
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @PostMapping("/login")
    public String loginUser( @ModelAttribute("todouser") todouser user) {
        return "login";
    }

    @GetMapping("/logout")
    public String logUserOut(@Valid @ModelAttribute("todouser") todouser user) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        userRepository.updateloggedin(false,userRepository.findByUsername(name).getUser_id());
        return "index";
    }

}
