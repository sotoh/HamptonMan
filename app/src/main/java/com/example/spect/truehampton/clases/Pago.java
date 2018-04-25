package com.example.spect.truehampton.clases;

import com.google.gson.annotations.SerializedName;

import java.sql.Date;

/**
 * Created by Ascenet on 13/04/2018.
 */

public class Pago {
    //id, amount, paymentstatus, paymenttype, created_at, updated_at, bookings_id
    @SerializedName("id")
    private int id_pago;
    @SerializedName("amount")
    private double cantidad;
    @SerializedName("paymentstatus")
    private String status;
    @SerializedName("paymenttype")
    private String tipo;
    @SerializedName("created_at")
    private Date created;
    @SerializedName("updated_at")
    private Date updated;
    @SerializedName("bookings_id")
    private int idReserva;

    public Pago(int id_pago, double cantidad, String status, String tipo, Date created, Date updated, int idReserva) {
        this.id_pago = id_pago;
        this.cantidad = cantidad;
        this.status = status;
        this.tipo = tipo;
        this.created = created;
        this.updated = updated;
        this.idReserva = idReserva;
    }

    public int getId_pago() {
        return id_pago;
    }

    public void setId_pago(int id_pago) {
        this.id_pago = id_pago;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }
}
