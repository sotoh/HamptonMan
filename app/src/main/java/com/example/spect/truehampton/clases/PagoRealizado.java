package com.example.spect.truehampton.clases;

import com.google.gson.annotations.SerializedName;

import java.sql.Date;

public class PagoRealizado {

    @SerializedName("id")
    private int id;
    @SerializedName("created_at")
    private Date created;
    @SerializedName("updated_at")
    private Date updated;
    @SerializedName("paymentcode")
    private String code;
    @SerializedName("paymentstatus")
    private String status;
    @SerializedName("payments_id")
    private int paymentId;

    public PagoRealizado(int id, Date created, Date updated, String code, String status, int paymentId) {
        this.id = id;
        this.created = created;
        this.updated = updated;
        this.code = code;
        this.status = status;
        this.paymentId = paymentId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }
}
