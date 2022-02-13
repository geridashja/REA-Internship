package com.todo.todo.controller;

import com.todo.todo.entity.Todo;
import com.todo.todo.entity.todouser;
import com.todo.todo.repository.TodoRepository;
import com.todo.todo.repository.todouserrepo;
import com.todo.todo.todouser.status;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String login() {
        return "login";
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }


    @PostMapping("/login")
    public String loginUser(@Valid @ModelAttribute("todouser") todouser user) {
//        List<todouser> users = userRepository.findAll();
//        for (todouser other : users) {
//            if (other.equals(user)) {
//                userRepository.updateloggedin(true,other.getUser_id());
//                return "userhome";
//            }
//        }
        return "login";
//        return "redirect:/registration?failure";
    }


//    @GetMapping("/userhome")
//    public String userhome() {
//        return "userhome";
//    }
//
//    //userhome
//    @ModelAttribute("todouser")
//    public todouser todouser()
//    {
//        return new todouser();
//    }
//
//    @PostMapping
//    public String userhome(@ModelAttribute("todouser") Principal principal)
//    {
//        return principal.getName();
//    }

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

    @GetMapping("/alltasks")
    public String getalltasks( RedirectAttributes attr){
        List<Todo> todos = Todorepository.findAll();
        attr.addFlashAttribute("todos",todos);
        System.out.println(todos);
        return "alltasks";
    }

}
