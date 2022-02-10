package com.todo.todo.controller;

import com.todo.todo.entity.todouser;
import com.todo.todo.repository.todouserrepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/registration")
public class RegistrationController {
    @Autowired
    todouserrepo userRepository;

    //postmap testing purposes
    @GetMapping("/merri")
    public List<todouser> merri(){
        List<todouser> users = userRepository.findAll();
        return  users;
    }

    @ModelAttribute("todouser")
    public todouser todouser()
    {
        return new todouser();
    }

    @GetMapping
    public String showRegistrationForm()
    {
        return "registration";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("todouser") todouser newUser)
    {
        userRepository.save(newUser);
        return "redirect:/registration?success";
    }

}