package com.example.mobileproject.activity.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mobileproject.R;
import com.example.mobileproject.adapter.admin.HostAdapter;
import com.example.mobileproject.apiKiet.ApiServiceKiet;
import com.example.mobileproject.model.HostModel;
import com.example.mobileproject.until.AppUtil;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HostDetailActivity extends AppCompatActivity {
    private Activity activity;
    ImageView imgHost;
    TextView tvHostName;
    TextView tvHostPhone;


    Button btnHostDanhSachPhong;
    Button btnHostCall;
    Button btnHostKhoaTaiKhoan;
    Button btnHostMoTaiKhoan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.host_detail);

        imgHost = findViewById(R.id.imgHostDetail);
        tvHostName = findViewById(R.id.tvNameHostDetail);
        tvHostPhone = findViewById(R.id.tvNameHostPhoneDetail);
        btnHostDanhSachPhong = findViewById(R.id.btnHostDanhSachPhong);
        btnHostCall = findViewById(R.id.btnHostCall);
        btnHostKhoaTaiKhoan = findViewById(R.id.btnHostKhoaTaiKhoan);
        btnHostMoTaiKhoan = findViewById(R.id.btnHostMoTaiKhoan);

        HostByIdApi(AppUtil.Id);

        btnHostKhoaTaiKhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LockHostApi(AppUtil.Id);
            }
        });

        btnHostMoTaiKhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UnLockHostApi(AppUtil.Id);
            }
        });
    }

    private void HostByIdApi(int key)
    {
        ApiServiceKiet.apiServiceKiet.getHostByIdAPI(key).enqueue(new Callback<HostModel>() {
            @Override
            public void onResponse(Call<HostModel> call, Response<HostModel> response) {
                Log.d("tinnhan", "thanh cong");
                HostModel host = (HostModel) response.body();

                Glide.with(getApplicationContext()).load(host.getHinhNguoiDung()).into(imgHost);
                tvHostName.setText(host.getTenNguoiDung());
                tvHostPhone.setText(host.getSoDienThoai());
            }

            @Override
            public void onFailure(Call<HostModel> call, Throwable t) {
                Log.d("tinnhan", "that bai");

            }
        });
    }

    private void LockHostApi(int key)
    {
        ApiServiceKiet.apiServiceKiet.getHostLockAccountAPI(key).enqueue(new Callback<HostModel>() {
            @Override
            public void onResponse(Call<HostModel> call, Response<HostModel> response) {

            }

            @Override
            public void onFailure(Call<HostModel> call, Throwable t) {

            }
        });

    }

    private void UnLockHostApi(int key)
    {
        ApiServiceKiet.apiServiceKiet.getHostUnLockAccountAPI(key).enqueue(new Callback<HostModel>() {
            @Override
            public void onResponse(Call<HostModel> call, Response<HostModel> response) {

            }

            @Override
            public void onFailure(Call<HostModel> call, Throwable t) {

            }
        });

    }
}