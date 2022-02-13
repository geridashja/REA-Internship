package com.todo.todo.controller;

import com.sun.security.auth.UserPrincipal;
import com.todo.todo.entity.Todo;
import com.todo.todo.entity.todouser;
import com.todo.todo.repository.todouserrepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/userhome")
public class userhomeController {


    @Autowired
    todouserrepo userRepository;

    //userhome
//    @ModelAttribute("todouser")
//    public String todouser(Principal principal) {
//        return principal.getName();
//    }
//    {
//        return principal.getName();
//    }
//    public UserDetails getCurrentUser(Authentication authentication) {
//        return (authentication == null) ? null : (UserDetails) authentication.getPrincipal();
//    }


    @GetMapping()
    public String userhome(Principal principal) {
        return "userhome";
    }


//    @PostMapping
//    //@ModelAttribute("todouser")
//    public String userhome( Principal principal)
//    {
//        return principal.getName();
//    }

//    @GetMapping("/logout")
//    public String logUserOut() {
//        List<todouser> users = userRepository.findAll();
//        for (todouser other : users) {
//            if(other.isLoggedin() == true){
//                userRepository.updateloggedin(false,other.getUser_id());
//            }
//            other.setLoggedin(false);
//        }
//        return "index";
//    }
//
//    @GetMapping("/alltasks")
//    public String getalltasks( RedirectAttributes attr){
//        List<Todo> todos = Todorepository.findAll();
//        attr.addFlashAttribute("todos",todos);
//        System.out.println(todos);
//        return "alltasks";
//    }

}


