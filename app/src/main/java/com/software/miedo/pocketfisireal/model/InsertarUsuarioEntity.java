package com.software.miedo.pocketfisireal.model;

import java.io.Serializable;

public class InsertarUsuarioEntity implements Serializable {

    private String username;
    private String password;
    private String mobile;

    public InsertarUsuarioEntity() {
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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
