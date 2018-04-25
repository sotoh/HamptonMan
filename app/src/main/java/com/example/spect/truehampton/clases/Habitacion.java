package com.example.spect.truehampton.clases;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Ascenet on 12/04/2018.
 */

public class Habitacion {
    //id, price, roomtype, floor, available
    @SerializedName("id")
    private String id_hab;
    @SerializedName("price")
    private String precio;
    @SerializedName("floor")
    private String piso;
    @SerializedName("roomtype")
    private String tipo;
    @SerializedName("available")
    private boolean disponibilidad;
    public Habitacion(String id_hab, String precio, String piso, String tipo, boolean disponibilidad) {
        this.id_hab = id_hab;
        this.precio = precio;
        this.piso = piso;
        this.tipo = tipo;
        this.disponibilidad = disponibilidad;
    }
    public Habitacion()
    {}
    public String getId_hab() {
        return id_hab;
    }

    public void setId_hab(String id_hab) {
        this.id_hab = id_hab;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getPiso() {
        return piso;
    }

    public void setPiso(String piso) {
        this.piso = piso;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public boolean isDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }
}
