package com.example.spect.truehampton.clases;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Ascenet on 13/04/2018.
 */

public class Recomendacion {
    //id, name, address, type, description
    @SerializedName("id")
    private int id_rec;
    @SerializedName("name")
    private String nombre;
    @SerializedName("address")
    private String direccion;
    @SerializedName("type")
    private String tipo;
    @SerializedName("description")
    private String descripcion;

    public Recomendacion(int id_rec, String nombre, String direccion, String tipo, String descripcion) {
        this.id_rec = id_rec;
        this.nombre = nombre;
        this.direccion = direccion;
        this.tipo = tipo;
        this.descripcion = descripcion;
    }

    public int getId_rec() {
        return id_rec;
    }

    public void setId_rec(int id_rec) {
        this.id_rec = id_rec;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
