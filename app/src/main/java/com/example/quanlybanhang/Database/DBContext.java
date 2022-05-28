package com.example.quanlybanhang.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.quanlybanhang.Entity.CTHoaDon;
import com.example.quanlybanhang.Entity.HoaDon;
import com.example.quanlybanhang.Entity.SanPham;

import java.util.ArrayList;
import java.util.List;

public class DBContext extends SQLiteOpenHelper {
    SQLiteDatabase database;

    public DBContext(@Nullable Context context) {
        super(context, "csdl.sqlite", null, 1);
        database = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table SanPham(" +
                "id integer primary key autoincrement," +
                "tenSP nvarchar(50)," +
                "loaiSP nvarchar(50)," +
                "giaBan integer," +
                "soluong integer)");
        sqLiteDatabase.execSQL("create table HoaDon(" +
                "id integer primary key autoincrement," +
                "tenKH nvarchar(100)," +
                "ngayBan nvarchar(50))");
        sqLiteDatabase.execSQL("create table CTHoaDon(" +
                "id integer primary key autoincrement," +
                "idHoaDon integer," +
                "idSP integer," +
                "soLuong integer)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public List<SanPham> getAllSanPhams() {
        List<SanPham> sanPhamList = new ArrayList<>();
        Cursor cursor = database.rawQuery("select * from SanPham", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            SanPham sanPham = new SanPham(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getInt(3), cursor.getInt(4));
            sanPhamList.add(sanPham);
            cursor.moveToNext();
        }
        return sanPhamList;
    }

    public void updateSanPham(SanPham sanPham, boolean isUpdate) {
        ContentValues values = new ContentValues();

        values.put("tenSP", sanPham.getTenSP());
        values.put("loaiSP", sanPham.getLoaiSP());
        values.put("giaBan", sanPham.getGiaBan());
        values.put("soLuong", sanPham.getSoLuong());
        if (isUpdate) {
            values.put("id", sanPham.getId());
            database.update("SanPham", values, "id = ?", new String[]{sanPham.getId() + ""});
        } else {
            database.insert("SanPham", null, values);
        }
    }

    public void deleteSanPham(int id) {
        database.delete("SanPham", "id = ?", new String[]{id + ""});
    }

    public List<HoaDon> getAllHoaDons() {
        List<HoaDon> hoaDonList = new ArrayList<>();
        Cursor cursor = database.rawQuery("select * from HoaDon", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            HoaDon hoaDon = new HoaDon(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
            hoaDonList.add(hoaDon);
            cursor.moveToNext();
        }
        return hoaDonList;
    }

    public void addHoaDon(HoaDon hoaDon) {
        ContentValues values = new ContentValues();
        values.put("tenKH", hoaDon.getTenKH());
        values.put("ngayBan", hoaDon.getNgayBan());
        database.insert("HoaDon", null, values);
    }


    public List<CTHoaDon> getCTHoaDon(int idHoaDon) {
        List<CTHoaDon> ctHoaDonList = new ArrayList<>();
        Cursor cursor = database.rawQuery("select CTHoaDon.id,CTHoaDon.idHoaDon,SanPham.tenSP,SanPham.giaBan,CTHoaDon.soLuong from CTHoaDon,SanPham where SanPham.id = CTHoaDon.idSP and CTHoaDon.idHoaDon = " + idHoaDon, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            CTHoaDon ctHoaDon = new CTHoaDon(cursor.getInt(0), cursor.getInt(1), cursor.getString(2), cursor.getInt(3), cursor.getInt(4));
            ctHoaDonList.add(ctHoaDon);
            cursor.moveToNext();
        }
        return ctHoaDonList;
    }

    public void addCTHoaDon(int idHoaDon, int idSanPham, int soLuong) {
        ContentValues values = new ContentValues();
        values.put("idHoaDon", idHoaDon);
        values.put("idSP", idSanPham);
        values.put("soLuong", soLuong);
        database.insert("CTHoaDon", null, values);
    }

}
