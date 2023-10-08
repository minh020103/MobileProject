package com.example.mobileproject.datamodel;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TaiKhoan{

    @SerializedName("sdt")
    private String tenDangNhap;
    @SerializedName("matkhau")
    private String matKhau;

    public TaiKhoan(String tenDangNhap, String matKhau) {
        this.tenDangNhap = tenDangNhap;
        this.matKhau = matKhau;
    }
    public TaiKhoan() {

    }

    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }
}
