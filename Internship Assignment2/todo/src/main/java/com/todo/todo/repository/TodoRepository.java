package com.todo.todo.repository;

import com.todo.todo.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {


    @Transactional
    @Modifying
    @Query(value="select distinct t.* from todostable t join usertable u on t.user_id =?1 where t.user_id = ?1",nativeQuery = true)
    List<Todo> todos_foruser(Long user_id);


    @Query(value="delete t from todostable t join usertable u on t.todo_id =?1",nativeQuery = true)
    void deletetodo(Long table_id);


}
