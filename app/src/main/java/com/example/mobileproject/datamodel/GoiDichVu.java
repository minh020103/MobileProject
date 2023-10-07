package com.example.mobileproject.datamodel;

import com.google.gson.annotations.SerializedName;

public class GoiDichVu {
    @SerializedName("Id")
    private int id;
    @SerializedName("ThoiHan")
    private int thoiHan;
    @SerializedName("SoLuongPhong")
    private int soLuongPhong;
    @SerializedName("GiaGoi")
    private int giaGoi;
    @SerializedName("TrangThai")
    private int trangThai;

    public GoiDichVu(int id, int thoiHan, int soLuongPhong, int giaGoi, int trangThai) {
        this.id = id;
        this.thoiHan = thoiHan;
        this.soLuongPhong = soLuongPhong;
        this.giaGoi = giaGoi;
        this.trangThai = trangThai;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getThoiHan() {
        return thoiHan;
    }

    public void setThoiHan(int thoiHan) {
        this.thoiHan = thoiHan;
    }

    public int getSoLuongPhong() {
        return soLuongPhong;
    }

    public void setSoLuongPhong(int soLuongPhong) {
        this.soLuongPhong = soLuongPhong;
    }

    public int getGiaGoi() {
        return giaGoi;
    }

    public void setGiaGoi(int giaGoi) {
        this.giaGoi = giaGoi;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public String toString() {
        return "GoiDichVu{" +
                "id=" + id +
                ", thoiHan=" + thoiHan +
                ", soLuongPhong=" + soLuongPhong +
                ", giaGoi=" + giaGoi +
                ", trangThai=" + trangThai +
                '}';
    }
}
