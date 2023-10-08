package com.example.mobileproject.activity.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.mobileproject.R;

public class PakageEditActivity extends AppCompatActivity {
    ImageView imgBackGoiDVSua;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pakage_edit_layout);

        imgBackGoiDVSua = findViewById(R.id.imgBackGoiDVSua);

        imgBackGoiDVSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}