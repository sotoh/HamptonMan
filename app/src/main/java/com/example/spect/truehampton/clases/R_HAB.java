package com.example.spect.truehampton.clases;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Ascenet on 13/04/2018.
 */

public class R_HAB {
    //bookings_id, rooms_id, nguests
    @SerializedName("rooms_id")
    private String habitacion;
    @SerializedName("bookings_id")
    private String reserva_h;
    @SerializedName("nguests")
    private int nguests;

    public R_HAB(String habitacion, String reserva_h, int nguests) {
        this.habitacion = habitacion;
        this.reserva_h = reserva_h;
        this.nguests = nguests;
    }

    public String getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(String habitacion) {
        this.habitacion = habitacion;
    }

    public String getReserva_h() {
        return reserva_h;
    }

    public void setReserva_h(String reserva_h) {
        this.reserva_h = reserva_h;
    }

    public int getNguests() {
        return nguests;
    }

    public void setNguests(int nguests) {
        this.nguests = nguests;
    }
}
