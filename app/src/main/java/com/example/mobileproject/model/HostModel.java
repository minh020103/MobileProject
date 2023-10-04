package com.example.mobileproject.model;

import com.google.gson.annotations.SerializedName;

public class HostModel {
    private int id;
    private int idTaiKhoan;
    private int idViThanhToan;
    private String hinhNguoiDung;
    private String tenNguoiDung;
    private String SoDienThoai;
    private String gioiTinh;
    private int xacThuc;

    public HostModel(int id, int idTaiKhoan, int idViThanhToan, String hinhNguoiDung, String tenNguoiDung, String soDienThoai, String gioiTinh, int xacThuc) {
        this.id = id;
        this.idTaiKhoan = idTaiKhoan;
        this.idViThanhToan = idViThanhToan;
        this.hinhNguoiDung = hinhNguoiDung;
        this.tenNguoiDung = tenNguoiDung;
        SoDienThoai = soDienThoai;
        this.gioiTinh = gioiTinh;
        this.xacThuc = xacThuc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdTaiKhoan() {
        return idTaiKhoan;
    }

    public void setIdTaiKhoan(int idTaiKhoan) {
        this.idTaiKhoan = idTaiKhoan;
    }

    public int getIdViThanhToan() {
        return idViThanhToan;
    }

    public void setIdViThanhToan(int idViThanhToan) {
        this.idViThanhToan = idViThanhToan;
    }

    public String getHinhNguoiDung() {
        return hinhNguoiDung;
    }

    public void setHinhNguoiDung(String hinhNguoiDung) {
        this.hinhNguoiDung = hinhNguoiDung;
    }

    public String getTenNguoiDung() {
        return tenNguoiDung;
    }

    public void setTenNguoiDung(String tenNguoiDung) {
        this.tenNguoiDung = tenNguoiDung;
    }

    public String getSoDienThoai() {
        return SoDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        SoDienThoai = soDienThoai;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public int getXacThuc() {
        return xacThuc;
    }

    public void setXacThuc(int xacThuc) {
        this.xacThuc = xacThuc;
    }

    @Override
    public String toString() {
        return "HostModel{" +
                "id=" + id +
                ", idTaiKhoan=" + idTaiKhoan +
                ", idViThanhToan=" + idViThanhToan +
                ", hinhNguoiDung='" + hinhNguoiDung + '\'' +
                ", tenNguoiDung='" + tenNguoiDung + '\'' +
                ", SoDienThoai='" + SoDienThoai + '\'' +
                ", gioiTinh='" + gioiTinh + '\'' +
                ", xacThuc=" + xacThuc +
                '}';
    }
}
