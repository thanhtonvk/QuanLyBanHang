package com.example.quanlybanhang.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quanlybanhang.Database.DBContext;
import com.example.quanlybanhang.Entity.CTHoaDon;
import com.example.quanlybanhang.Entity.SanPham;
import com.example.quanlybanhang.R;

import java.util.List;

public class ChiTietHoaDonActivity extends AppCompatActivity {

    EditText edt_soluong;
    AutoCompleteTextView edt_timkiem;
    Button btn_them;
    ListView lv_chitiethoadon;
    DBContext db;
    List<CTHoaDon> ctHoaDonList;
    ArrayAdapter<CTHoaDon> adapter;
    Spinner sp_sanpham;
    ArrayAdapter<SanPham> spAdapter;
    int idHoaDon;
    SanPham sanPham;
    List<SanPham> sanPhamList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_hoa_don);
        anhXaView();
        loadDuLieu();
        onClick();
    }

    private void anhXaView() {
        edt_soluong = findViewById(R.id.edt_soluong);
        btn_them = findViewById(R.id.btn_them);
        lv_chitiethoadon = findViewById(R.id.lv_chitiethoadon);
        db = new DBContext(getApplicationContext());
        sp_sanpham = findViewById(R.id.sp_sanpham);
        edt_timkiem = findViewById(R.id.edt_timkiem);
    }

    private void loadDuLieu() {
        idHoaDon = getIntent().getIntExtra("idHoaDon", 0);
        ctHoaDonList = db.getCTHoaDon(idHoaDon);
        adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, ctHoaDonList);
        lv_chitiethoadon.setAdapter(adapter);
        sanPhamList = db.getAllSanPhams();
        spAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, sanPhamList);
        sp_sanpham.setAdapter(spAdapter);
        edt_timkiem.setAdapter(adapter);
    }

    private void onClick() {
        sp_sanpham.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                sanPham = sanPhamList.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                sanPham = sanPhamList.get(0);
            }
        });
        btn_them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!edt_soluong.getText().toString().equals("")) {
                    db.addCTHoaDon(idHoaDon, sanPham.getId(), Integer.parseInt(edt_soluong.getText().toString()));
                    loadDuLieu();
                    edt_soluong.setText("");
                } else {
                    Toast.makeText(getApplicationContext(), "số lượng không đưuọc để rỗng", Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}