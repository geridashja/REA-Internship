package com.todo.todo.controller;

import com.todo.todo.entity.Todo;
import com.todo.todo.entity.todouser;
import com.todo.todo.repository.TodoRepository;
import com.todo.todo.repository.todouserrepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/login/add")
public class TodoController {

    @Autowired
    todouserrepo userRepository;

    @Autowired
    private TodoRepository Todorepository;

    @GetMapping
    public String showAddForm() {
        return "userhomeadd";
    }


    @ModelAttribute("Todo")
    public Todo Todo() {
        return new Todo();
    }

    @PostMapping()
    public String registerTodo(@ModelAttribute("Todo") Todo todo) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        todo.setUser(userRepository.findByUsername(name));
        todo.setStatus(false);
        Todorepository.save(todo);
        return "redirect:/userhome";

    }
}