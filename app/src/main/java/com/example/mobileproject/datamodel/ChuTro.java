package com.example.mobileproject.datamodel;

import com.google.gson.annotations.SerializedName;

public class ChuTro {
    @SerializedName("Id")
    private int id;
    @SerializedName("IdTaiKhoan")
    private int idTaiKhoan;
    @SerializedName("HinhNguoiDung")
    private String hinhNguoiDung;
    @SerializedName("TenNguoiDung")
    private String tenNguoiDung;
    @SerializedName("SoDienThoai")
    private String SoDienThoai;
    @SerializedName("GioiTinh")
    private String gioiTinh;
    @SerializedName("IdDichVu")
    private int idDichVu;
    @SerializedName("soTaiKhoanNganHang")
    private String soTaiKhoanNganHang;
    @SerializedName("tenChuTaiKhoanNganHang")
    private String tenChuTaiKhoanNganHang;
    @SerializedName("XacThuc")
    private int xacThuc;

    public ChuTro(int id, int idTaiKhoan, String hinhNguoiDung, String tenNguoiDung, String soDienThoai, String gioiTinh, int idDichVu, String soTaiKhoanNganHang, String tenChuTaiKhoanNganHang, int xacThuc) {
        this.id = id;
        this.idTaiKhoan = idTaiKhoan;
        this.hinhNguoiDung = hinhNguoiDung;
        this.tenNguoiDung = tenNguoiDung;
        SoDienThoai = soDienThoai;
        this.gioiTinh = gioiTinh;
        this.idDichVu = idDichVu;
        this.soTaiKhoanNganHang = soTaiKhoanNganHang;
        this.tenChuTaiKhoanNganHang = tenChuTaiKhoanNganHang;
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

    public int getIdDichVu() {
        return idDichVu;
    }

    public void setIdDichVu(int idDichVu) {
        this.idDichVu = idDichVu;
    }

    public String getSoTaiKhoanNganHang() {
        return soTaiKhoanNganHang;
    }

    public void setSoTaiKhoanNganHang(String soTaiKhoanNganHang) {
        this.soTaiKhoanNganHang = soTaiKhoanNganHang;
    }

    public String getTenChuTaiKhoanNganHang() {
        return tenChuTaiKhoanNganHang;
    }

    public void setTenChuTaiKhoanNganHang(String tenChuTaiKhoanNganHang) {
        this.tenChuTaiKhoanNganHang = tenChuTaiKhoanNganHang;
    }

    public int getXacThuc() {
        return xacThuc;
    }

    public void setXacThuc(int xacThuc) {
        this.xacThuc = xacThuc;
    }

    @Override
    public String toString() {
        return "ChuTro{" +
                "id=" + id +
                ", idTaiKhoan=" + idTaiKhoan +
                ", hinhNguoiDung='" + hinhNguoiDung + '\'' +
                ", tenNguoiDung='" + tenNguoiDung + '\'' +
                ", SoDienThoai='" + SoDienThoai + '\'' +
                ", gioiTinh='" + gioiTinh + '\'' +
                ", idDichVu=" + idDichVu +
                ", soTaiKhoanNganHang='" + soTaiKhoanNganHang + '\'' +
                ", tenChuTaiKhoanNganHang='" + tenChuTaiKhoanNganHang + '\'' +
                ", xacThuc=" + xacThuc +
                '}';
    }
}
