package com.example.mobileproject.activity.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.mobileproject.R;

public class PakageAddActivity extends AppCompatActivity {
    ImageView imgBackGoiDVThem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pakage_add_layout);

        imgBackGoiDVThem = findViewById(R.id.imgBackGoiDVThem);

        imgBackGoiDVThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}