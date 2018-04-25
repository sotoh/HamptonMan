package com.example.spect.truehampton.clases;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Ascenet on 13/04/2018.
 */

public class Tipocliente {
    //id, customertype, discount
    /*
    * "customertype": {
        "id": 1,
        "customertype": "Normal",
        "discount": "0.00"
    },*/
    @SerializedName("id")
    private int idTipoCliente;
    @SerializedName("customertype")
    private String tipo;
    @SerializedName("discount")
    private String descuento;

    public Tipocliente(int idTipoCliente, String tipo, String descuento) {
        this.idTipoCliente = idTipoCliente;
        this.tipo = tipo;
        this.descuento = descuento;
    }

    public int getIdTipoCliente() {
        return idTipoCliente;
    }

    public void setIdTipoCliente(int idTipoCliente) {
        this.idTipoCliente = idTipoCliente;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescuento() {
        return descuento;
    }

    public void setDescuento(String descuento) {
        this.descuento = descuento;
    }
}
