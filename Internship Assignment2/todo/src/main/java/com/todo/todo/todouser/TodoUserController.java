package com.todo.todo.todouser;

import com.todo.todo.entity.todouser;
import com.todo.todo.repository.todouserrepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class TodoUserController {
    @Autowired
    todouserrepo userRepository;

    @CrossOrigin()
    @PostMapping("/users/register")
    public status registerUser(@Valid @RequestBody todouser newUser) {
        List<todouser> users = userRepository.findAll();
        if(users.size() == 0){
            userRepository.save(newUser);
        }
        else{
            for (todouser user : users) {
                if (user.equals(newUser)) {
                    return status.USER_ALREADY_EXISTS;
                }
            }
            userRepository.save(newUser);
        }
        return status.SUCCESS;
    }

    @CrossOrigin()
    @PostMapping("/users/login")
    public status loginUser(@Valid @RequestBody todouser user) {
        List<todouser> users = userRepository.findAll();

        for (todouser other : users) {
            if (other.equals(user)) {
                userRepository.save(user);
                return status.SUCCESS;
            }
        }
        return status.FAILURE;
    }

    @CrossOrigin()
    @PostMapping("/users/logout")
    public status logUserOut(@Valid @RequestBody todouser user) {
        List<todouser> users = userRepository.findAll();

        for (todouser other : users) {
            if (other.equals(user)) {
                userRepository.save(user);
                return status.SUCCESS;
            }
        }

        return status.FAILURE;
    }

    @CrossOrigin()
    @DeleteMapping("/users/all")
    public status deleteUsers() {
        userRepository.deleteAll();
        return status.SUCCESS;
    }
}
