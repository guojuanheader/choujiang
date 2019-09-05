package com.draw.user.pojo;


import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "cj_user")
public class User {
    @Id
    private String  id;
    private String  username;
    private String  password;
    private String  head;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }
}
