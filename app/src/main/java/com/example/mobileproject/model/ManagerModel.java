package com.example.mobileproject.model;

public class Data {
    private String hinh;
    private String ten;

    public Data(String hinh, String ten) {
        this.hinh = hinh;
        this.ten = ten;
    }

    public String getHinh() {
        return hinh;
    }

    public void setHinh(String hinh) {
        this.hinh = hinh;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }
}
