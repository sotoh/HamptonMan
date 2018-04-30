package com.example.spect.truehampton.clases;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Ascenet on 13/04/2018.
 */

public class Direccion {
    //
    /*
    * "address": {
        "id": 3,
        "street": "avenida juta",
        "streetNumber": "45",
        "city": "torreon",
        "state": "coahuila",
        "country": "mexico",
        "zip": 27450,
        "customers_id": 3
    },*/
    @SerializedName("id")
    private int id_direccion;
    @SerializedName("zip")
    private int cpostal;
    @SerializedName("street")
    private String calle;
    @SerializedName("streetNumber")
    private String ncalle;
    @SerializedName("city")
    private String ciudad;
    @SerializedName("country")
    private String pais;
    @SerializedName("customers_id")
    private int clienteId;
    @SerializedName("state")
    private String estado;

    public Direccion(int id_direccion, int cpostal, String calle, String ncalle, String ciudad, String pais, int clienteId, String estado) {
        this.id_direccion = id_direccion;
        this.cpostal = cpostal;
        this.calle = calle;
        this.ncalle = ncalle;
        this.ciudad = ciudad;
        this.pais = pais;
        this.clienteId = clienteId;
        this.estado = estado;
    }

    public int getId_direccion() {
        return id_direccion;
    }

    public void setId_direccion(int id_direccion) {
        this.id_direccion = id_direccion;
    }

    public int getCpostal() {
        return cpostal;
    }

    public void setCpostal(int cpostal) {
        this.cpostal = cpostal;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNcalle() {
        return ncalle;
    }

    public void setNcalle(String ncalle) {
        this.ncalle = ncalle;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
