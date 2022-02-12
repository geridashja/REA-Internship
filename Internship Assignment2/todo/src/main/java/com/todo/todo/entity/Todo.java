package com.todo.todo.entity;

import javax.persistence.*;
import java.sql.Date;


@Entity
@Table(name = "todostable")
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long todo_id;


    private String name;
    private String description;
    private Date deadline;
    private Boolean status; //completed or not

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    private todouser user;

    public Todo() {
    }

    public Todo(String name, String description, Date deadline, Boolean status, todouser user) {
        this.name = name;
        this.description = description;
        this.deadline = deadline;
        this.status = status;
        this.user = user;
    }

    public Long getTodo_id() {
        return todo_id;
    }

    public void setTodo_id(Long todo_id) {
        this.todo_id = todo_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public todouser getUser() {
        return user;
    }

    public void setUser(todouser user) {
        this.user = user;
    }
}
