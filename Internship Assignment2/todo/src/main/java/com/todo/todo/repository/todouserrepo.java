package com.todo.todo.repository;

import com.todo.todo.entity.todouser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface todouserrepo extends JpaRepository<todouser, Long> {
    @Transactional
    @Modifying
    @Query(value="update usertable set loggedin = ?1 where user_id = ?2",nativeQuery = true)
    int updateloggedin(boolean status, Long id);

//    @Query(value="select e from usertable e where e.username = ?1",nativeQuery = true)
//    todouser findbyusername(String username);
    todouser findByUsername(String username);

}
