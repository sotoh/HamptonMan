package com.example.spect.truehampton.clases;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Ascenet on 13/04/2018.
 */

public class Eventos {
    //id, startime, dateevent, name, address, description
    @SerializedName("id")
    private String id_event;
    @SerializedName("startime")
    private String hora;
    @SerializedName("dateevent")
    private String fecha;
    @SerializedName("name")
    private String nombre;
    @SerializedName("address")
    private String lugar;
    @SerializedName("description")
    private String descripcion;

    public Eventos(String id_event, String hora, String fecha, String nombre, String lugar, String descripcion) {
        this.id_event = id_event;
        this.hora = hora;
        this.fecha = fecha;
        this.nombre = nombre;
        this.lugar = lugar;
        this.descripcion = descripcion;
    }

    public String getId_event() {
        return id_event;
    }

    public void setId_event(String id_event) {
        this.id_event = id_event;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
