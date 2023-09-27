package com.example.mobileproject.datamodel;

public class Luong {
    private int idTaiKhoanQuanLy;
    private int tienLuong;
    private int xacThucCuaNguoiQuanLy;

    public Luong(int idTaiKhoanQuanLy, int tienLuong, int xacThucCuaNguoiQuanLy) {
        this.idTaiKhoanQuanLy = idTaiKhoanQuanLy;
        this.tienLuong = tienLuong;
        this.xacThucCuaNguoiQuanLy = xacThucCuaNguoiQuanLy;
    }

    public int getIdTaiKhoanQuanLy() {
        return idTaiKhoanQuanLy;
    }

    public void setIdTaiKhoanQuanLy(int idTaiKhoanQuanLy) {
        this.idTaiKhoanQuanLy = idTaiKhoanQuanLy;
    }

    public int getTienLuong() {
        return tienLuong;
    }

    public void setTienLuong(int tienLuong) {
        this.tienLuong = tienLuong;
    }

    public int getXacThucCuaNguoiQuanLy() {
        return xacThucCuaNguoiQuanLy;
    }

    public void setXacThucCuaNguoiQuanLy(int xacThucCuaNguoiQuanLy) {
        this.xacThucCuaNguoiQuanLy = xacThucCuaNguoiQuanLy;
    }
}
