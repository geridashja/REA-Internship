
package com.todo.todo.controller;

import com.todo.todo.entity.Todo;
import com.todo.todo.entity.todouser;
import com.todo.todo.repository.TodoRepository;
import com.todo.todo.repository.todouserrepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("userhome/completed/{id}")
public class UpdatesController {
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
    public String updatestatus(@PathVariable Long id){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        todouser foruser = userRepository.findByUsername(name);

        Optional<Todo> todo2 = Todorepository.findById(id);
        Todo todo3 = todo2.get();
        Todorepository.updatetodostatus(true,todo3.getTodo_id());
        return "redirect:/userhome";
    }
}
