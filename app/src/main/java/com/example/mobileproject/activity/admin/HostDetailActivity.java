package com.example.mobileproject.activity.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

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
    ImageView imgHost;
    TextView tvHostName;
    TextView tvHostPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.host_detail);

        imgHost = findViewById(R.id.imgHostDetail);
        tvHostName = findViewById(R.id.tvNameHostDetail);
        tvHostPhone = findViewById(R.id.tvNameHostPhoneDetail);

        HostByIdApi(AppUtil.Id);
    }

    private void HostByIdApi(int key)
    {
        ApiServiceKiet.apiServiceKiet.getHostByIdAPI(key).enqueue(new Callback<HostModel>() {
            @Override
            public void onResponse(Call<HostModel> call, Response<HostModel> response) {
                Log.d("tinnhan", "thanh cong");
                HostModel host = (HostModel) response.body();

                tvHostName.setText(host.getTenNguoiDung());
                tvHostPhone.setText(host.getSoDienThoai());
            }

            @Override
            public void onFailure(Call<HostModel> call, Throwable t) {
                Log.d("tinnhan", "that bai");

            }
        });
    }
}