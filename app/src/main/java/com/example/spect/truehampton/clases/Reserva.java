package com.example.spect.truehampton.clases;

import com.google.gson.annotations.SerializedName;

import java.sql.Date;

/**
 * Created by Ascenet on 12/04/2018.
 */

public class Reserva {
    //id, created_at, is_canceled, customers_id, updated_at, deleted_at, checkin, checkout, differentcheck
    /*
    *  "bookings": [
        {
            "id": 2,
            "created_at": "2018-04-22 19:35:12",
            "customers_id": 3,
            "updated_at": "2018-04-22 19:35:12",
            "deleted_at": null,
            "checkin": "2020-12-01",
            "checkout": "2020-12-12",
            "differentcheck": 0
        },*/
    @SerializedName("id")
    private int id_re;
    @SerializedName("customers_id")
    private int idCliente;
    @SerializedName("created_at")
    private Date created_r;
    @SerializedName("updated_at")
    private Date updated_r;
    @SerializedName("checkin")
    private String checkin;
    @SerializedName("checkout")
    private String checkout;
    @SerializedName("differentcheck")
    private int checktime;
    @SerializedName("deleted_at")
    private Date delete;
    @SerializedName("is_canceled")
    private boolean cancel;
    @SerializedName("checktime")
    private Verificacion check;

    public Verificacion getCheck() {
        return check;
    }

    public void setCheck(Verificacion check) {
        this.check = check;
    }

    public Reserva(int id_re, int idCliente, Date created_r,
                   Date updated_r, String checkin, String checkout,
                   int checktime, Date delete, boolean cancel, Verificacion check) {
        this.id_re = id_re;
        this.idCliente = idCliente;
        this.created_r = created_r;
        this.updated_r = updated_r;
        this.checkin = checkin;
        this.checkout = checkout;
        this.checktime = checktime;
        this.delete = delete;
        this.cancel = cancel;
        this.check = check;
    }

    public int getId_re() {
        return id_re;
    }

    public void setId_re(int id_re) {
        this.id_re = id_re;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public Date getCreated_r() {
        return created_r;
    }

    public void setCreated_r(Date created_r) {
        this.created_r = created_r;
    }

    public Date getUpdated_r() {
        return updated_r;
    }

    public void setUpdated_r(Date updated_r) {
        this.updated_r = updated_r;
    }

    public String getCheckin() {
        return checkin;
    }

    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    public String getCheckout() {
        return checkout;
    }

    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }

    public int getChecktime() {
        return checktime;
    }

    public void setChecktime(int checktime) {
        this.checktime = checktime;
    }

    public Date getDelete() {
        return delete;
    }

    public void setDelete(Date delete) {
        this.delete = delete;
    }

    public boolean isCancel() {
        return cancel;
    }

    public void setCancel(boolean cancel) {
        this.cancel = cancel;
    }
}
