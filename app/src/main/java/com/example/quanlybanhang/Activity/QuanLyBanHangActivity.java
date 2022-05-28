package com.example.quanlybanhang.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quanlybanhang.Database.DBContext;
import com.example.quanlybanhang.Entity.HoaDon;
import com.example.quanlybanhang.R;

import java.util.List;

public class QuanLyBanHangActivity extends AppCompatActivity {

    Button btn_them;
    AutoCompleteTextView edt_timkiem;
    EditText edt_tenkh, edt_ngay;
    ListView lv_hoadon;
    List<HoaDon> hoaDonList;
    ArrayAdapter<HoaDon> adapter;
    DBContext db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_ban_hang);
        db = new DBContext(getApplicationContext());
        anhXa();
        loadDuLieu();
        onClick();
    }

    private void anhXa() {
        btn_them = findViewById(R.id.btn_them);
        edt_timkiem = findViewById(R.id.edt_timkiem);
        edt_tenkh = findViewById(R.id.edt_tenkh);
        edt_ngay = findViewById(R.id.edt_ngay);
        lv_hoadon = findViewById(R.id.lv_hoadon);
    }

    private void loadDuLieu() {
        hoaDonList = db.getAllHoaDons();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, hoaDonList);
        lv_hoadon.setAdapter(adapter);
        edt_timkiem.setAdapter(adapter);
    }

    private void onClick() {
        btn_them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HoaDon hoaDon = new HoaDon();
                hoaDon.setTenKH(edt_tenkh.getText().toString());
                hoaDon.setNgayBan(edt_ngay.getText().toString());
                db.addHoaDon(hoaDon);
                edt_tenkh.setText("");
                edt_ngay.setText("");
                loadDuLieu();
            }
        });
        lv_hoadon.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                HoaDon hoaDon = hoaDonList.get(i);
                Intent intent = new Intent(getApplicationContext(), ChiTietHoaDonActivity.class);
                intent.putExtra("idHoaDon", hoaDon.getId());
                startActivity(intent);
            }
        });
    }
}