package com.example.quanlybanhang.Entity;

public class HoaDon {
    public HoaDon(int id, String tenKH, String ngayBan) {
        this.id = id;
        this.tenKH = tenKH;
        this.ngayBan = ngayBan;
    }

    public HoaDon() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public String getNgayBan() {
        return ngayBan;
    }

    public void setNgayBan(String ngayBan) {
        this.ngayBan = ngayBan;
    }

    private int id;
    private String tenKH, ngayBan;

    @Override
    public String toString() {
        return String.format(" Tên khách hàng: %s \n Ngày bán: %s", tenKH, ngayBan);
    }
}
