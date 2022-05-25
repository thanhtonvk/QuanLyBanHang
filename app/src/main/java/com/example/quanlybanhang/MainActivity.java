package com.example.quanlybanhang;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quanlybanhang.Activity.QuanLyBanHangActivity;
import com.example.quanlybanhang.Activity.QuanLySanPhamActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_quanlysp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), QuanLySanPhamActivity.class));
            }
        });
        findViewById(R.id.btn_banhang).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), QuanLyBanHangActivity.class));
            }
        });
    }
}