package com.example.mobileproject.datamodel;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TaiKhoan{
    @SerializedName("tenTaiKhoan")
    private String tenTaiKhoan;
    @SerializedName("matKhau")
    private String matKhau;
    @SerializedName("trangThai")
    private int trangThai;
    @SerializedName("loaiTaiKhoan")
    private int loaiTaiKhoan;
    @SerializedName("email")
    private String email;

    public TaiKhoan(String tenTaiKhoan, String matKhau, int trangThai, int loaiTaiKhoan, String email) {
        this.tenTaiKhoan = tenTaiKhoan;
        this.matKhau = matKhau;
        this.trangThai = trangThai;
        this.loaiTaiKhoan = loaiTaiKhoan;
        this.email = email;
    }
    public TaiKhoan() {

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

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public int getLoaiTaiKhoan() {
        return loaiTaiKhoan;
    }

    public void setLoaiTaiKhoan(int loaiTaiKhoan) {
        this.loaiTaiKhoan = loaiTaiKhoan;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
