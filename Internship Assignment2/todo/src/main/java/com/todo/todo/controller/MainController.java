package com.todo.todo.controller;

import com.todo.todo.entity.todouser;
import com.todo.todo.repository.todouserrepo;
import com.todo.todo.todouser.status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@Controller
public class MainController {
    @Autowired
    todouserrepo userRepository;

    //postmap testing purposes
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }


    @PostMapping("/login")
    public String loginUser(@Valid @ModelAttribute("todouser") todouser user) {
        List<todouser> users = userRepository.findAll();
        for (todouser other : users) {
            if (other.equals(user)) {
                userRepository.updateloggedin(true,other.getUser_id());
                return "userhome";
            }
        }
        return "redirect:/registration?failure";
    }

    @GetMapping("/logout")
    public String logUserOut() {
        List<todouser> users = userRepository.findAll();
        for (todouser other : users) {
            if(other.isLoggedin() == true){
                userRepository.updateloggedin(false,other.getUser_id());
            }
                other.setLoggedin(false);
        }
        return "index";
    }

}
