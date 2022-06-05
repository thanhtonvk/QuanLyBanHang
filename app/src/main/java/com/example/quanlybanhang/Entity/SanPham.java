package com.example.quanlybanhang.Entity;

public class SanPham {
    private int id;
    private String tenSP, loaiSP;
    private int giaBan, soLuong;

    public SanPham() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public String getLoaiSP() {
        return loaiSP;
    }

    public void setLoaiSP(String loaiSP) {
        this.loaiSP = loaiSP;
    }

    public int getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(int giaBan) {
        this.giaBan = giaBan;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public SanPham(int id, String tenSP, String loaiSP, int giaBan, int soLuong) {
        this.id = id;
        this.tenSP = tenSP;
        this.loaiSP = loaiSP;
        this.giaBan = giaBan;
        this.soLuong = soLuong;
    }

    @Override
    public String toString() {
        return String.format(" Tên sản phẩm: %s \n Loại sản phẩm: %s \n Giá bán: %s vnđ \n Số lượng: %s", tenSP, loaiSP, giaBan, soLuong);
    }
}
