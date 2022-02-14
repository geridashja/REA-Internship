package com.todo.todo.controller;

import com.sun.security.auth.UserPrincipal;
import com.todo.todo.entity.Todo;
import com.todo.todo.entity.todouser;
import com.todo.todo.repository.TodoRepository;
import com.todo.todo.repository.todouserrepo;
import com.todo.todo.todouser.status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/userhome")
public class userhomeController {


    @Autowired
    todouserrepo userRepository;

    @Autowired
    private TodoRepository Todorepository;

    @ModelAttribute("todos")
    public List<Todo> sendtodos() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        todouser foruser = userRepository.findByUsername(name);
        return Todorepository.todos_foruser(foruser.getUser_id());
    }

    @GetMapping()
    public String userhome() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName(); //get logged in username
        userRepository.updateloggedin(true,userRepository.findByUsername(name).getUser_id());
        return "userhome";
    }

    @ModelAttribute("todos")
    @DeleteMapping("/delete/{id}")
    public String deleteTodo(@PathVariable("id") Long id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        todouser foruser = userRepository.findByUsername(name);
        System.out.println(foruser.getUsername());
//        Optional<Todo> todo2 = Todorepository.findById(id);
//        Todo todo = todo2.get();
//        Todorepository.deletetodo(todo.getTodo_id());
        return "redirect:/userhome";
    }

//
//    @GetMapping("/alltasks")
//    public String getalltasks( RedirectAttributes attr){
//        List<Todo> todos = Todorepository.findAll();
//        attr.addFlashAttribute("todos",todos);
//        System.out.println(todos);
//        return "alltasks";
//    }

}


