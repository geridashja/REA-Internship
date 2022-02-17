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
    @Query(value="select distinct t.* from todostable t join usertable u on t.user_id =?1 where t.user_id = ?1 ORDER BY t.status asc",nativeQuery = true)
    List<Todo> todos_foruser(Long user_id);

    @Transactional
    @Modifying
    @Query(value="update todostable set status = ?1 where todo_id = ?2 AND status = false",nativeQuery = true)
    void updatetodostatus(boolean status, Long todo_id);

    @Transactional
    @Modifying
    @Query(value="select distinct t.* from todostable t join usertable u on t.user_id =?1 where t.user_id = ?1 and t.status=false",nativeQuery = true)
    List<Todo> completedTodos(Long user_id);

    @Transactional
    @Modifying
    @Query(value="select distinct t from todostable t join usertable u on t.user_id =?1 where t.user_id = ?1 and t.status=false",nativeQuery = true)
    List<Todo> NotcompletedTodos(Long user_id);
}
