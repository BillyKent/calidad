package com.software.miedo.pocketfisireal.model;

import java.io.Serializable;

public class CambiarContraEntity implements Serializable {

    private String usuario;
    private String oldpass;
    private String newpass;

    public CambiarContraEntity() {
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getOldpass() {
        return oldpass;
    }

    public void setOldpass(String oldpass) {
        this.oldpass = oldpass;
    }

    public String getNewpass() {
        return newpass;
    }

    public void setNewpass(String newpass) {
        this.newpass = newpass;
    }
}
