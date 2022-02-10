package com.todo.todo.repository;

import com.todo.todo.entity.todouser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface todouserrepo extends JpaRepository<todouser, Long> {
//    @Query("SELECT t.username, t.password FROM usertable t WHERE t.username = ?1 AND t.password = ?2")
//    todouser findByusername(String name,String password);
}
