package com.example.mobileproject.datamodel;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TaiKhoan{

    private int id;
    private String tenTaiKhoan;
    private String matKhau;
    private int loaiTaiKhoan;
    private int trangThai;

    public TaiKhoan(int id, String tenTaiKhoan, String matKhau, int loaiTaiKhoan, int trangThai) {
        this.id = id;
        this.tenTaiKhoan = tenTaiKhoan;
        this.matKhau = matKhau;
        this.loaiTaiKhoan = loaiTaiKhoan;
        this.trangThai = trangThai;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenTaiKhoan() {
        return tenTaiKhoan;
    }

    public void setTenTaiKhoan(String tenTaiKhoan) {
        this.tenTaiKhoan = tenTaiKhoan;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public int getLoaiTaiKhoan() {
        return loaiTaiKhoan;
    }

    public void setLoaiTaiKhoan(int loaiTaiKhoan) {
        this.loaiTaiKhoan = loaiTaiKhoan;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public String toString() {
        return "TaiKhoan{" +
                "id=" + id +
                ", tenTaiKhoan='" + tenTaiKhoan + '\'' +
                ", matKhau='" + matKhau + '\'' +
                ", loaiTaiKhoan=" + loaiTaiKhoan +
                ", trangThai=" + trangThai +
                '}';
    }
}
