package com.example.spect.truehampton.clases;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Ascenet on 12/04/2018.
 */

public class Telefono {
    //id, type, number, customers_id
    /*{
            "id": 1,
            "type": "Movil",
            "number": "6363525526",
            "customers_id": 3
        }*/
    @SerializedName("id")
    private int id;
    @SerializedName("type")
    private String tipo;
    @SerializedName("number")
    private String telefono;
    @SerializedName("customers_id")
    private int idCliente;

    public Telefono(int id, String tipo, String telefono, int idCliente) {
        this.id = id;
        this.tipo = tipo;
        this.telefono = telefono;
        this.idCliente = idCliente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }
}
