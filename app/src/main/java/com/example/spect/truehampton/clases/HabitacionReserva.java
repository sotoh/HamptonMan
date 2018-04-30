package com.example.spect.truehampton.clases;

import com.google.gson.annotations.SerializedName;

public class HabitacionReserva {
    private String TipoHabitacion;
    private int huespedes;

    public HabitacionReserva( String tipoHabitacion, int huespedes) {
        TipoHabitacion = tipoHabitacion;
        this.huespedes = huespedes;
    }
    public HabitacionReserva()
    {}
    public String getTipoHabitacion() {
        return TipoHabitacion;
    }

    public void setTipoHabitacion(String tipoHabitacion) {
        TipoHabitacion = tipoHabitacion;
    }

    public int getHuespedes() {
        return huespedes;
    }

    public void setHuespedes(int huespedes) {
        this.huespedes = huespedes;
    }
}
