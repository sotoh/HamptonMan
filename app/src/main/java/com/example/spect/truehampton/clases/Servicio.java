package com.example.spect.truehampton.clases;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Ascenet on 13/04/2018.
 */

public class Servicio {
    @SerializedName("id")
    private int id_ser;
    @SerializedName("name")
    private String nombre;
    @SerializedName("description")
    private String descripcion;

    public Servicio(int id_ser, String nombre, String descripcion) {
        this.id_ser = id_ser;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public int getId_ser() {
        return id_ser;
    }

    public void setId_ser(int id_ser) {
        this.id_ser = id_ser;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
