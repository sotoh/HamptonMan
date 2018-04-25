package com.example.spect.truehampton.clases;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by Ascenet on 13/04/2018.
 */

public class Tarjeta {
    //id, payments_id, created_at, updated_at, paymentcode, status
    /*
    * "creditcards": [
        {
            "id": 1,
            "number": "6363663",
            "bank": "hola",
            "expirationdate": "2020-12-12",
            "customers_id": 3
        }
    ],*/
    @SerializedName("id")
    private int id_tr;
    @SerializedName("number")
    private String numbercard;
    @SerializedName("bank")
    private String bank;
    @SerializedName("expirationdate")
    private Date expiration;
    @SerializedName("customers_id")
    private int idCliente;

    public Tarjeta(int id_tr, String numbercard, String bank, Date expiration, int idCliente) {
        this.id_tr = id_tr;
        this.numbercard = numbercard;
        this.bank = bank;
        this.expiration = expiration;
        this.idCliente = idCliente;
    }

    public int getId_tr() {
        return id_tr;
    }

    public void setId_tr(int id_tr) {
        this.id_tr = id_tr;
    }

    public String getNumbercard() {
        return numbercard;
    }

    public void setNumbercard(String numbercard) {
        this.numbercard = numbercard;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public Date getExpiration() {
        return expiration;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }
}
