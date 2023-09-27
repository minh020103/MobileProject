package com.example.mobileproject.models;


import com.google.gson.annotations.SerializedName;

public class Account {

    @SerializedName("sdt")
    private String sdt;
    @SerializedName("ten")
    private String ten;
    @SerializedName("matkhau")
    private String matkhau;

    public Account(String sdt, String ten, String matkhau) {
        this.sdt = sdt;
        this.ten = ten;
        this.matkhau = matkhau;
    }
    public Account() {

    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }

    @Override
    public String toString() {
        return ten+", "+sdt+", "+matkhau;
    }
}
