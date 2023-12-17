package com.example.mobileproject.activity.admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mobileproject.R;
import com.example.mobileproject.api.admin.ApiServiceNghiem;
import com.example.mobileproject.datamodel.ChinhSach;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailPolicyActivity extends AppCompatActivity {


    TextView tvChinhSach;
    Button btnChinhSua;
    AppCompatImageView ic_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_policy);
        anhXa();
        setDuLieu();
        setSuKien();
    }
    private void setSuKien(){
        btnChinhSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailPolicyActivity.this,EditPolicyActivity.class);
                startActivity(intent);
            }
        });
        ic_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private void setDuLieu(){
        Call<ChinhSach> call = ApiServiceNghiem.apiService.layChinhSachXuong(1);
        call.enqueue(new Callback<ChinhSach>() {
            @Override
            public void onResponse(Call<ChinhSach> call, Response<ChinhSach> response) {
                if (response.body()!=null) {
                    tvChinhSach.setText(response.body().getNoiDungChinhSach());
                }
            }

            @Override
            public void onFailure(Call<ChinhSach> call, Throwable t) {

            }
        });
    }
    private void anhXa(){
        tvChinhSach = findViewById(R.id.tvChinhSach);
        btnChinhSua = findViewById(R.id.chinhSua);
        ic_back= findViewById(R.id.icon_back);
    }

}