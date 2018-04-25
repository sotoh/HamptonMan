package com.example.spect.truehampton.clases;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Ascenet on 12/04/2018.
 */

public class Usuario {
    //
    /*
    *  "user": {
        "id": 3,
        "password": "123456",
        "email": "spectro52@hotmail.com",
        "created_at": "2018-04-20 05:11:43",
        "customers_id": 3,
        "updated_at": "2018-04-20 05:11:43",
        "deleted_at": null
    },*/
    @SerializedName("id")
    private int id;
    @SerializedName("email")
    private String email;
    @SerializedName("created_at")
    private java.sql.Date create;
    @SerializedName("updated_at")
    private java.sql.Date update;
    @SerializedName("deleted_at")
    private java.sql.Date delete;
    @SerializedName("password")
    private String contrasena;
    @SerializedName("customers_id")
    private int idclient;

    public Usuario(int id, String email, java.sql.Date create, java.sql.Date update, java.sql.Date delete, String contrasena, int idclient) {
        this.id = id;
        this.email = email;
        this.create = create;
        this.update = update;
        this.delete = delete;
        this.contrasena = contrasena;
        this.idclient = idclient;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public java.sql.Date getCreate() {
        return create;
    }

    public void setCreate(java.sql.Date create) {
        this.create = create;
    }

    public java.sql.Date getUpdate() {
        return update;
    }

    public void setUpdate(java.sql.Date update) {
        this.update = update;
    }

    public java.sql.Date getDelete() {
        return delete;
    }

    public void setDelete(java.sql.Date delete) {
        this.delete = delete;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public int getIdclient() {
        return idclient;
    }

    public void setIdclient(int idclient) {
        this.idclient = idclient;
    }
}
