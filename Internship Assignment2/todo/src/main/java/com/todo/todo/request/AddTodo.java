package com.todo.todo.request;


public class AddTodo {

    private String description;

    public AddTodo(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
