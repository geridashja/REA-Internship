package com.todo.todo.controller;

import com.todo.todo.entity.Todo;
import com.todo.todo.entity.todouser;
import com.todo.todo.repository.TodoRepository;
import com.todo.todo.repository.todouserrepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
    public String showAddForm(Principal principal)
    {
        return "userhomeadd";
    }

//    @ModelAttribute("todouser")
//    public todouser todouser()
//    {
//        return new todouser();
//    }

    @ModelAttribute("Todo")
    public Todo Todo()
    {
        return new Todo();
    }

    @PostMapping()
    public String registerTodo(@ModelAttribute("Todo") Todo todo,Principal principal)
    {
        List<todouser> users = userRepository.findAll();
        for (todouser other : users) {
            if(other.getUsername() == principal.getName()){
                Todorepository.save(todo);
                return "redirect:/userhome";
            }
        }
//        Todorepository.save(todo);
        return "redirect:/userhome";

    }
    public String currentUserName(Principal principal) {
        return principal.getName();
    }
//    @RequestMapping("/")
//    public String index(Todo todo) {
//        ArrayList<Todo> todoList = (ArrayList<Todo>) repository.findAll();
//        //model.addAttribute("items", todoList);
////        model.addAttribute("newitem", new TodoItem());
////        model.addAttribute("items", new TodoListViewModel(todoList));
//        return "index";
//    }

//    @RequestMapping("/add")
//    public String addTodo(@Valid @ModelAttribute("todo") Todo todo) {
//        TodoItem item = new TodoItem(requestItem.getCategory(), requestItem.getName());
//        repository.save(item);
//        return "redirect:/";
//    }

//    @RequestMapping("/update")
//    public String updateTodo(@ModelAttribute TodoListViewModel requestItems) {
//        for (TodoItem requestItem : requestItems.getTodoList() ) {
//            TodoItem item = new TodoItem(requestItem.getCategory(), requestItem.getName());
//            item.setComplete(requestItem.isComplete());
//            item.setId(requestItem.getId());
//            repository.save(item);
//        }
//        return "redirect:/";
//    }
}
