package com.example.spect.truehampton.clases;

import com.google.gson.annotations.SerializedName;

import java.sql.Date;

/**
 * Created by Ascenet on 13/04/2018.
 */

public class Cancelacion {
    @SerializedName("id")
    private int id_cancel;
    @SerializedName("canceltime")
    private Date cancelaciontime;
    @SerializedName("bookings_id")
    private int reserva;

    public Cancelacion(int id_cancel, Date cancelaciontime, int reserva) {
        this.id_cancel = id_cancel;
        this.cancelaciontime = cancelaciontime;
        this.reserva = reserva;
    }

    public int getId_cancel() {
        return id_cancel;
    }

    public void setId_cancel(int id_cancel) {
        this.id_cancel = id_cancel;
    }

    public Date getCancelaciontime() {
        return cancelaciontime;
    }

    public void setCancelaciontime(Date cancelaciontime) {
        this.cancelaciontime = cancelaciontime;
    }

    public int getReserva() {
        return reserva;
    }

    public void setReserva(int reserva) {
        this.reserva = reserva;
    }
}
