package com.example.mobileproject.activity.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.mobileproject.R;

public class PakageAddActivity extends AppCompatActivity {
    ImageView imgBackGoiDVThem;
    EditText edtThoiHanGoiDVThem;
    EditText edtSoLuongPhongGoiDVThem;
    EditText edtGiaGoiDVThem;
    Button btnGoiDVThem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pakage_add_layout);

        imgBackGoiDVThem = findViewById(R.id.imgBackGoiDVThem);
        edtThoiHanGoiDVThem = findViewById(R.id.edtThoiHanGoiDVThem);
        edtSoLuongPhongGoiDVThem = findViewById(R.id.edtSoLuongPhongGoiDVThem);
        edtGiaGoiDVThem = findViewById(R.id.edtGiaGoiDVThem);
        btnGoiDVThem = findViewById(R.id.btnGoiDVThem);

        imgBackGoiDVThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}