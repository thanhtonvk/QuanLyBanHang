package com.example.quanlybanhang;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DangNhapActivity extends AppCompatActivity {

    EditText edt_tk, edt_mk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        edt_tk = findViewById(R.id.edt_tk);
        edt_mk = findViewById(R.id.edt_mk);
        findViewById(R.id.btn_dangky).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), DangKyActivity.class));
            }
        });
        findViewById(R.id.btn_dangnhap).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edt_tk.getText().toString().equals("admin") && edt_mk.getText().toString().equals("admin")) {
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                } else {
                    Toast.makeText(getApplicationContext(), "Tài khoản mật khẩu không chính xác", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}