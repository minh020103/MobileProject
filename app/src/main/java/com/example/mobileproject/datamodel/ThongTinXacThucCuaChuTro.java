package com.example.mobileproject.datamodel;

import com.google.gson.annotations.SerializedName;

public class XacThucChuTro {
    @SerializedName("id")
    private int id;
    @SerializedName("idChuTro")
    private int idChoTro;
    @SerializedName("cccdMatTruoc")
    private String cccdMatTruoc;
    @SerializedName("cccdMatSau")
    private String cccdMatSau;
    @SerializedName("trangThaiXacThuc")
    private int trangThaiXacThuc;

    public XacThucChuTro(int id, int idChoTro, String cccdMatTruoc, String cccdMatSau, int trangThaiXacThuc) {
        this.id = id;
        this.idChoTro = idChoTro;
        this.cccdMatTruoc = cccdMatTruoc;
        this.cccdMatSau = cccdMatSau;
        this.trangThaiXacThuc = trangThaiXacThuc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdChoTro() {
        return idChoTro;
    }

    public void setIdChoTro(int idChoTro) {
        this.idChoTro = idChoTro;
    }

    public String getCccdMatTruoc() {
        return cccdMatTruoc;
    }

    public void setCccdMatTruoc(String cccdMatTruoc) {
        this.cccdMatTruoc = cccdMatTruoc;
    }

    public String getCccdMatSau() {
        return cccdMatSau;
    }

    public void setCccdMatSau(String cccdMatSau) {
        this.cccdMatSau = cccdMatSau;
    }

    public int getTrangThaiXacThuc() {
        return trangThaiXacThuc;
    }

    public void setTrangThaiXacThuc(int trangThaiXacThuc) {
        this.trangThaiXacThuc = trangThaiXacThuc;
    }

    @Override
    public String toString() {
        return "XacThucChuTro{" +
                "id=" + id +
                ", idChoTro=" + idChoTro +
                ", cccdMatTruoc='" + cccdMatTruoc + '\'' +
                ", cccdMatSau='" + cccdMatSau + '\'' +
                ", trangThaiXacThuc=" + trangThaiXacThuc +
                '}';
    }
}
