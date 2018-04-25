package com.example.spect.truehampton.clases;

import com.google.gson.annotations.SerializedName;

import java.sql.Date;

/**
 * Created by Ascenet on 13/04/2018.
 */

public class Orden {
    //customers_id, services_id, created_at
    @SerializedName("services_id")
    private int service;
    @SerializedName("customers_id")
    private int cliente;
    @SerializedName("created_at")
    private Date created;

    public Orden(int service, int cliente, Date created) {
        this.service = service;
        this.cliente = cliente;
        this.created = created;
    }

    public int getService() {
        return service;
    }

    public void setService(int service) {
        this.service = service;
    }

    public int getCliente() {
        return cliente;
    }

    public void setCliente(int cliente) {
        this.cliente = cliente;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
