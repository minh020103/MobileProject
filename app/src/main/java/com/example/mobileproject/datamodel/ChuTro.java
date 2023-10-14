package com.example.mobileproject.datamodel;

public class ChuTro {
    private int id;
    private int idTaiKhoan;
    private String hinh;
    private String ten;
    private String soDienThoai;
    private int idGoi;
    private String soTaiKhoanNganHang;
    private String tenChuTaiKhoanNganHang;
    private int xacThuc;
    private YeuCauXacThuc yeuCauXacThuc;
    private TaiKhoan taiKhoan;

    public ChuTro(int id, int idTaiKhoan, String hinh, String ten, String soDienThoai, int idGoi, String soTaiKhoanNganHang, String tenChuTaiKhoanNganHang, int xacThuc, YeuCauXacThuc yeuCauXacThuc, TaiKhoan taiKhoan) {
        this.id = id;
        this.idTaiKhoan = idTaiKhoan;
        this.hinh = hinh;
        this.ten = ten;
        this.soDienThoai = soDienThoai;
        this.idGoi = idGoi;
        this.soTaiKhoanNganHang = soTaiKhoanNganHang;
        this.tenChuTaiKhoanNganHang = tenChuTaiKhoanNganHang;
        this.xacThuc = xacThuc;
        this.yeuCauXacThuc = yeuCauXacThuc;
        this.taiKhoan = taiKhoan;
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

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public int getIdGoi() {
        return idGoi;
    }

    public void setIdGoi(int idGoi) {
        this.idGoi = idGoi;
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

    public YeuCauXacThuc getYeuCauXacThuc() {
        return yeuCauXacThuc;
    }

    public void setYeuCauXacThuc(YeuCauXacThuc yeuCauXacThuc) {
        this.yeuCauXacThuc = yeuCauXacThuc;
    }

    public TaiKhoan getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(TaiKhoan taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    @Override
    public String toString() {
        return "ChuTro{" +
                "id=" + id +
                ", idTaiKhoan=" + idTaiKhoan +
                ", hinh='" + hinh + '\'' +
                ", ten='" + ten + '\'' +
                ", soDienThoai='" + soDienThoai + '\'' +
                ", idGoi=" + idGoi +
                ", soTaiKhoanNganHang='" + soTaiKhoanNganHang + '\'' +
                ", tenChuTaiKhoanNganHang='" + tenChuTaiKhoanNganHang + '\'' +
                ", xacThuc=" + xacThuc +
                ", yeuCauXacThuc=" + yeuCauXacThuc +
                ", taiKhoan=" + taiKhoan +
                '}';
    }
}
