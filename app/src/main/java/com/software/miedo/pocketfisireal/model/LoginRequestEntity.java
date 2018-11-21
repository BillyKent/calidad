package com.software.miedo.pocketfisireal.model;

import java.io.Serializable;

public class LoginRequestEntity implements Serializable {

    private String username;

    private String password;

    public LoginRequestEntity() {
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
}
