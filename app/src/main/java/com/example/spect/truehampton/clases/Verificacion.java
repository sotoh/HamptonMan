package com.example.spect.truehampton.clases;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Ascenet on 13/04/2018.
 */

public class Verificacion {
//id, checkintime, bookings_id
    @SerializedName("id")
    private String idVerification;
    @SerializedName("checkintime")
    private String checkintime;
    @SerializedName("bookings_id")
    private int reservaId;

    public Verificacion(String idVerification, String checkintime, int reserva) {
        this.idVerification = idVerification;
        this.checkintime = checkintime;
        this.reservaId = reserva;
    }

    public String getIdVerification() {
        return idVerification;
    }

    public void setIdVerification(String idVerification) {
        this.idVerification = idVerification;
    }

    public String getCheckintime() {
        return checkintime;
    }

    public void setCheckintime(String checkintime) {
        this.checkintime = checkintime;
    }

    public int getReserva() {
        return reservaId;
    }

    public void setReserva(int reserva) {
        this.reservaId = reserva;
    }
}
