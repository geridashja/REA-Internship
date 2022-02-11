package com.todo.todo.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "todostable")
public class Todo {


    private @Id @GeneratedValue int todo_id;
    private String name;
    private String description;
    private LocalDate deadline;
    private Boolean status; //completed or not

    public Todo() {
    }

    public Todo(int id, String name, String description, LocalDate deadline, Boolean status) {
        this.todo_id = id;
        this.name = name;
        this.description = description;
        this.deadline = deadline;
        this.status = status;
    }

    public int getId() {
        return todo_id;
    }

    public void setId(int id) {
        this.todo_id = id;
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

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
