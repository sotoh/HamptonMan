package com.example.spect.truehampton.clases;

import com.google.gson.annotations.SerializedName;

import java.sql.Date;

public class Cliente {
    /*
    *  "id": 3,
    "name": "hector",
    "lastname": "miguel",
    "secondlastname": "sotin",
    "numbervisits": 1,
    "birthday": "1994-05-05",
    "customertypes_id": 1,
    "created_at": "2018-04-20 05:11:43",
    "updated_at": "2018-04-20 05:11:43",*/
    @SerializedName("id")
    int idCliente;
    @SerializedName("lastname")
    String apellidoPaterno;
    @SerializedName("secondlastname")
    String apellidoMaterno;
    @SerializedName("name")
    String nombre;
    @SerializedName("numbervisits")
    int visitas;
    @SerializedName("birthday")
    Date nacimiento;
    @SerializedName("created_at")
    private java.sql.Date create;
    @SerializedName("updated_at")
    private java.sql.Date update;
    @SerializedName("customertypes_id")
    private int tipocliente;
    @SerializedName("user")
    private Usuario usuario;

    public Cliente(int idCliente, String apellidoPaterno,
                   String apellidoMaterno, String nombre, int visitas,
                   Date nacimiento, Date create, Date update, int tipocliente, Usuario usuario) {
        this.idCliente = idCliente;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.nombre = nombre;
        this.visitas = visitas;
        this.nacimiento = nacimiento;
        this.create = create;
        this.update = update;
        this.tipocliente = tipocliente;
        this.usuario = usuario;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getVisitas() {
        return visitas;
    }

    public void setVisitas(int visitas) {
        this.visitas = visitas;
    }

    public Date getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(Date nacimiento) {
        this.nacimiento = nacimiento;
    }

    public Date getCreate() {
        return create;
    }

    public void setCreate(Date create) {
        this.create = create;
    }

    public Date getUpdate() {
        return update;
    }

    public void setUpdate(Date update) {
        this.update = update;
    }

    public int getTipocliente() {
        return tipocliente;
    }

    public void setTipocliente(int tipocliente) {
        this.tipocliente = tipocliente;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}