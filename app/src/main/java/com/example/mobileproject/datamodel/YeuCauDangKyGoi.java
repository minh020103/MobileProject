package com.example.mobileproject.datamodel;

public class YeuCauDangKyGoi {
    private int id;
    private int  idChuTro;
    private int idGoi;
    private int trangThaiXacThuc;
    private String hinhAnhChuyenKhoan;

    private ChuTro chuTro;
    private Goi goi;

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

    public int getIdGoi() {
        return idGoi;
    }

    public void setIdGoi(int idGoi) {
        this.idGoi = idGoi;
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

    public Goi getGoi() {
        return goi;
    }

    public void setGoi(Goi goi) {
        this.goi = goi;
    }

    public String getHinhAnhChuyenKhoan() {
        return hinhAnhChuyenKhoan;
    }

    public void setHinhAnhChuyenKhoan(String hinhAnhChuyenKhoan) {
        this.hinhAnhChuyenKhoan = hinhAnhChuyenKhoan;
    }
}
