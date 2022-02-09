package com.todo.todo.todouser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface todouserrepo extends JpaRepository<todouser, Long> {
}
