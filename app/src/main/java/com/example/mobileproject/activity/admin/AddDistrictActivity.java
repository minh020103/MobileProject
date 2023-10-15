package com.example.mobileproject.activity.admin;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import com.example.mobileproject.R;

public class AddDistrictActivity extends AppCompatActivity {
    AppCompatImageView ic_back;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_district);
        anhXa();
        ic_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private void anhXa(){
        ic_back = findViewById(R.id.icon_back);
    }
}
