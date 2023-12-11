package com.example.mobileproject.datamodel;

import java.io.Serializable;

public class Quan {
    private int id;
    private String tenQuan;
    private String hinh;
    private int trangThai;

    public Quan(int id, String tenQuan, String hinh, int trangThai) {
        this.id = id;
        this.tenQuan = tenQuan;
        this.hinh = hinh;
        this.trangThai = trangThai;
    }
    public Quan() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenQuan() {
        return tenQuan;
    }

    public void setTenQuan(String tenQuan) {
        this.tenQuan = tenQuan;
    }

    public String getHinh() {
        return hinh;
    }

    public void setHinh(String hinh) {
        this.hinh = hinh;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }
}
