package com.example.mobileproject.datamodel;

public class DangKyDichVu {
    private int id;
    private int idChuTro;
    private int idGoi;
    private String ngayDangKi;
    private int trangThaiXacThuc;

    public DangKyDichVu(int id, int idChuTro, int idGoi, String ngayDangKi, int trangThaiXacThuc) {
        this.id = id;
        this.idChuTro = idChuTro;
        this.idGoi = idGoi;
        this.ngayDangKi = ngayDangKi;
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

    public int getIdGoi() {
        return idGoi;
    }

    public void setIdGoi(int idGoi) {
        this.idGoi = idGoi;
    }

    public String getNgayDangKi() {
        return ngayDangKi;
    }

    public void setNgayDangKi(String ngayDangKi) {
        this.ngayDangKi = ngayDangKi;
    }

    public int getTrangThaiXacThuc() {
        return trangThaiXacThuc;
    }

    public void setTrangThaiXacThuc(int trangThaiXacThuc) {
        this.trangThaiXacThuc = trangThaiXacThuc;
    }
}
