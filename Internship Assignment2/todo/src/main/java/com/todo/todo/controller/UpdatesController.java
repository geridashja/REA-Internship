
package com.todo.todo.controller;

import com.todo.todo.entity.Todo;
import com.todo.todo.entity.todouser;
import com.todo.todo.repository.TodoRepository;
import com.todo.todo.repository.todouserrepo;
import com.todo.todo.todouser.status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("userhome/completed/{}")
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
    public String updatestatus(@ModelAttribute("todos") Todo todo){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        todouser foruser = userRepository.findByUsername(name);

        Optional<Todo> todo2 = Todorepository.findById(todo.getTodo_id());
        Todo todo3 = todo2.get();
        todo3.setStatus(true);
        Todorepository.save(todo3);
//        List<Todo> alltodosforuser = Todorepository.todos_foruser(foruser.getUser_id());
//        for (Todo other : alltodosforuser) {
//            if (other.getTodo_id() == todo3.getTodo_id()) {
//                if(other.getStatus() == false){
//                    todo3.setStatus(true);
//                    Todorepository.delete(todo);
//                    Todorepository.save(todo3);
//                }
//            }
//        }
//        Todorepository.updatetodostatus(true,todo3.getTodo_id());
        return "redirect:/userhome";
    }
}
