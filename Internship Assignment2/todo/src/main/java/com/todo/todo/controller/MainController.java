package com.todo.todo.controller;

import com.sun.security.auth.UserPrincipal;
import com.todo.todo.entity.Todo;
import com.todo.todo.entity.todouser;
import com.todo.todo.repository.TodoRepository;
import com.todo.todo.repository.todouserrepo;
import com.todo.todo.services.todouserService;
import com.todo.todo.todouser.status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;


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
        String name = auth.getName(); //get logged in username
        userRepository.updateloggedin(false,userRepository.findByUsername(name).getUser_id());
        return "index";
    }

}
