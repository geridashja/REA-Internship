package com.todo.todo.controller;

import com.todo.todo.entity.todouser;
import com.todo.todo.repository.todouserrepo;
import com.todo.todo.todouser.status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;


@Controller
public class MainController {
    @Autowired
    todouserrepo userRepository;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/")
    public String home()
    {
        return "index";
    }


    @PostMapping("/login")
    public String loginUser(@Valid @ModelAttribute("todouser") todouser user) {
//        List<todouser> users = userRepository.findByusername();
        List<todouser> users = userRepository.findAll();
        for (todouser other : users) {
            if (other.equals(user)) {
                user.setLoggedIn(true);
//                userRepository.save(user);
                return "userhome";
            }
        }
        return "failed";
    }

    @GetMapping("/logout")
    public String logUserOut( ) {
//        List<todouser> users = userRepository.findAll();
//        for (todouser other : users) {
//            if (other.equals(user)) {
//                user.setLoggedIn(false);
//                userRepository.save(user);
//                return "index";
//            }
//        }
        return "index";
    }
//    @GetMapping("/registration")
//    public String registration()
//    {
//        return "registration";
//    }
}
