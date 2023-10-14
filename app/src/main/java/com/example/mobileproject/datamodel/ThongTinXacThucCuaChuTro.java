package com.example.mobileproject.datamodel;

import com.google.gson.annotations.SerializedName;

public class ThongTinXacThucCuaChuTro {
    @SerializedName("id")
    private int id;
    @SerializedName("idChuTro")
    private int idChuTro;
    @SerializedName("cccdMatTruoc")
    private String cccdMatTruoc;
    @SerializedName("cccdMatSau")
    private String cccdMatSau;
    @SerializedName("trangThaiXacThuc")
    private int trangThaiXacThuc;

    public ThongTinXacThucCuaChuTro(int id, int idChuTro, String cccdMatTruoc, String cccdMatSau, int trangThaiXacThuc) {
        this.id = id;
        this.idChuTro = idChuTro;
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

    public int getIdChuTro() {
        return idChuTro;
    }

    public void setIdChuTro(int idChuTro) {
        this.idChuTro = idChuTro;
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
        return "ThongTinXacThucCuaChuTro{" +
                "id=" + id +
                ", idChuTro=" + idChuTro +
                ", cccdMatTruoc='" + cccdMatTruoc + '\'' +
                ", cccdMatSau='" + cccdMatSau + '\'' +
                ", trangThaiXacThuc=" + trangThaiXacThuc +
                '}';
    }
}
