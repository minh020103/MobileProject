package com.example.mobileproject.datamodel;

public class YeuCauXacThuc {
    private int id;
    private int idChuTro;
    private String cccdMatTruoc;
    private String cccdMatSau;
    private int trangThaiXacThuc;

    private ChuTro chuTro;

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

    public ChuTro getChuTro() {
        return chuTro;
    }

    public void setChuTro(ChuTro chuTro) {
        this.chuTro = chuTro;
    }
}
