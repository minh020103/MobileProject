package com.example.mobileproject.datamodel;

public class TienIch {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String tenTienIch;
    private String hinh;
    private int trangThai;

    public TienIch(int id,String tenTienIch, String hinh, int trangThai) {
        this.id= id;
        this.tenTienIch = tenTienIch;
        this.hinh = hinh;
        this.trangThai = trangThai;
    }
    public TienIch() {

    }

    public String getTenTienIch() {
        return tenTienIch;
    }

    public void setTenTienIch(String tenTienIch) {
        this.tenTienIch = tenTienIch;
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
