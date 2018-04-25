package com.example.spect.truehampton.clases;

import com.google.gson.annotations.SerializedName;

import java.sql.Date;

/**
 * Created by Ascenet on 13/04/2018.
 */

public class Horariolim {
    //id, day, begin_at, rooms_id
    @SerializedName("id")
    private String id_hor;
    @SerializedName("day")
    private String dia;
    @SerializedName("begin_at")
    private Date begin_at;
    @SerializedName("rooms_id")
    private int hab;

    public Horariolim(String id_hor, String dia, Date begin_at, int hab) {
        this.id_hor = id_hor;
        this.dia = dia;
        this.begin_at = begin_at;
        this.hab = hab;
    }

    public String getId_hor() {
        return id_hor;
    }

    public void setId_hor(String id_hor) {
        this.id_hor = id_hor;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public Date getBegin_at() {
        return begin_at;
    }

    public void setBegin_at(Date begin_at) {
        this.begin_at = begin_at;
    }

    public int getHab() {
        return hab;
    }

    public void setHab(int hab) {
        this.hab = hab;
    }
}
