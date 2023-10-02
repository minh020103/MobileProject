package com.example.mobileproject.model;

public class ManagerModel {
    private int idTaiKhoanQuanLi;
    private String tenNguoiQuanLi;
    private String hinhNguoiQuanLi;
    private String sdtNguoiQuanLi;
    private String tinhQuanLi;
    private int soTienDaNhan;

    public ManagerModel(int idTaiKhoanQuanLi, String tenNguoiQuanLi, String hinhNguoiQuanLi, String sdtNguoiQuanLi, String tinhQuanLi, int soTienDaNhan) {
        this.idTaiKhoanQuanLi = idTaiKhoanQuanLi;
        this.tenNguoiQuanLi = tenNguoiQuanLi;
        this.hinhNguoiQuanLi = hinhNguoiQuanLi;
        this.sdtNguoiQuanLi = sdtNguoiQuanLi;
        this.tinhQuanLi = tinhQuanLi;
        this.soTienDaNhan = soTienDaNhan;
    }

    public int getIdTaiKhoanQuanLi() {
        return idTaiKhoanQuanLi;
    }

    public void setIdTaiKhoanQuanLi(int idTaiKhoanQuanLi) {
        this.idTaiKhoanQuanLi = idTaiKhoanQuanLi;
    }

    public String getTenNguoiQuanLi() {
        return tenNguoiQuanLi;
    }

    public void setTenNguoiQuanLi(String tenNguoiQuanLi) {
        this.tenNguoiQuanLi = tenNguoiQuanLi;
    }

    public String getHinhNguoiQuanLi() {
        return hinhNguoiQuanLi;
    }

    public void setHinhNguoiQuanLi(String hinhNguoiQuanLi) {
        this.hinhNguoiQuanLi = hinhNguoiQuanLi;
    }

    public String getSdtNguoiQuanLi() {
        return sdtNguoiQuanLi;
    }

    public void setSdtNguoiQuanLi(String sdtNguoiQuanLi) {
        this.sdtNguoiQuanLi = sdtNguoiQuanLi;
    }

    public String getTinhQuanLi() {
        return tinhQuanLi;
    }

    public void setTinhQuanLi(String tinhQuanLi) {
        this.tinhQuanLi = tinhQuanLi;
    }

    public int getSoTienDaNhan() {
        return soTienDaNhan;
    }

    public void setSoTienDaNhan(int soTienDaNhan) {
        this.soTienDaNhan = soTienDaNhan;
    }
}
