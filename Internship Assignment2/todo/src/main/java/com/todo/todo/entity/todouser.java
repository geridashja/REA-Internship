package com.todo.todo.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "usertable")
public class todouser {

    private @Id @GeneratedValue int user_id;
    private String username;
    private String password;

    public todouser() {
    }

    @OneToMany(cascade = CascadeType.ALL) //Because one user can have more than one todo
    private List<Todo> todos_foruser = new ArrayList<Todo>();

    public todouser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public long getId() {
        return user_id;
    }

    public void setId(int id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof todouser)) return false;
        todouser user = (todouser) o;
        return Objects.equals(username, user.username) &&
                Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user_id, username, password);
    }

}
