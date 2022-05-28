package com.example.quanlybanhang.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quanlybanhang.Database.DBContext;
import com.example.quanlybanhang.Entity.SanPham;
import com.example.quanlybanhang.R;

import java.util.List;

public class QuanLySanPhamActivity extends AppCompatActivity {

    EditText edt_tensp, edt_loaisp, edt_giaban, edt_soluong;
    Button btn_them, btn_sua, btn_xoa;
    AutoCompleteTextView edt_timkiem;
    ListView lv_sanpham;
    ArrayAdapter<SanPham> adapter;
    DBContext dbContext;
    List<SanPham> sanPhamList;
    SanPham sanPham;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_san_pham);
        dbContext = new DBContext(this);
        anhXa();
        loadDuLieu();
        onClick();
    }

    private void loadDuLieu() {
        sanPhamList = dbContext.getAllSanPhams();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, sanPhamList);
        lv_sanpham.setAdapter(adapter);
        edt_timkiem.setAdapter(adapter);
    }

    private void onClick() {
        btn_them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SanPham sanPham = getSanPham();
                dbContext.updateSanPham(sanPham, false);
                edt_tensp.setText("");
                edt_loaisp.setText("");
                edt_giaban.setText("");
                edt_soluong.setText("");
                loadDuLieu();
            }
        });
        lv_sanpham.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                sanPham = sanPhamList.get(i);
                edt_tensp.setText(sanPham.getTenSP());
                edt_loaisp.setText(sanPham.getLoaiSP());
                edt_giaban.setText(sanPham.getGiaBan() + "");
                edt_soluong.setText(sanPham.getSoLuong() + "");
            }
        });
        btn_sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sanPham != null) {
                    int id = sanPham.getId();
                    SanPham sanPham = getSanPham();
                    sanPham.setId(id);
                    dbContext.updateSanPham(sanPham, true);
                    loadDuLieu();
                } else {
                    Toast.makeText(getApplicationContext(), "chưa chọn sản phẩm cần sửa", Toast.LENGTH_LONG).show();
                }
            }
        });
        btn_xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sanPham != null) {
                    dbContext.deleteSanPham(sanPham.getId());
                    loadDuLieu();
                } else {
                    Toast.makeText(getApplicationContext(), "chưa chọn sản phẩm cần sửa", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void anhXa() {
        edt_tensp = findViewById(R.id.edt_ten);
        edt_loaisp = findViewById(R.id.edt_loai);
        edt_giaban = findViewById(R.id.edt_gia);
        btn_them = findViewById(R.id.btn_them);
        btn_sua = findViewById(R.id.btn_capnhat);
        btn_xoa = findViewById(R.id.btn_xoa);
        edt_timkiem = findViewById(R.id.edt_timkiem);
        lv_sanpham = findViewById(R.id.lv_sanpham);
        edt_soluong = findViewById(R.id.edt_soluong);
    }

    private SanPham getSanPham() {
        SanPham sanPham = new SanPham();
        sanPham.setTenSP(edt_tensp.getText().toString());
        sanPham.setGiaBan(Integer.parseInt(edt_giaban.getText().toString()));
        sanPham.setLoaiSP(edt_loaisp.getText().toString());
        sanPham.setSoLuong(Integer.parseInt(edt_soluong.getText().toString()));
        return sanPham;
    }
}