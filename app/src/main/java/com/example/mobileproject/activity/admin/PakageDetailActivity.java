package com.example.mobileproject.activity.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mobileproject.R;
import com.example.mobileproject.api.ApiServiceKiet;
import com.example.mobileproject.appuntil.AppUntil;
import com.example.mobileproject.datamodel.ChuTro;
import com.example.mobileproject.datamodel.GoiDichVu;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PakageDetailActivity extends AppCompatActivity {

    TextView tvThoiHanGoiDVChiTiet;
    TextView tvSoLuongPhongGoiDVChiTiet;
    TextView tvGiaGoiDVChiTiet;
    TextView tvTrangThaiGoiDVChiTiet;
    Button btnSuaGoiDVChiTiet;
    Button btnMoKhoaGoiDVChiTiet;
    Button btnKhoaGoiDVChiTiet;
    ImageView imgBackGoiDVFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pakage_detail_layout);

        tvThoiHanGoiDVChiTiet = findViewById(R.id.tvthoiHanGoiDVChiTiet);
        tvSoLuongPhongGoiDVChiTiet = findViewById(R.id.tvSoLuongPhongGoiDVChiTiet);
        tvGiaGoiDVChiTiet = findViewById(R.id.tvGiaGoiDVChiTiet);
        tvTrangThaiGoiDVChiTiet = findViewById(R.id.tvTrangThaiGoiDVChiTiet);
        btnSuaGoiDVChiTiet = findViewById(R.id.btnSuaGoiDVChiTiet);
        btnMoKhoaGoiDVChiTiet = findViewById(R.id.btnMoGoiDVChiTiet);
        btnKhoaGoiDVChiTiet = findViewById(R.id.btnKhoaGoiDVChiTiet);
        imgBackGoiDVFragment = findViewById(R.id.imgBackGoiDVFragment);

        imgBackGoiDVFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        PakageByIdAPI(AppUntil.ID_GOI_DICH_VU);

    }

    private void PakageByIdAPI(int key)
    {
        ApiServiceKiet.apiServiceKiet.getPakageByIdAPI(key).enqueue(new Callback<GoiDichVu>() {
            @Override
            public void onResponse(Call<GoiDichVu> call, Response<GoiDichVu> response) {
                GoiDichVu host = response.body();

                tvThoiHanGoiDVChiTiet.setText(String.valueOf(host.getThoiHan()));
                tvSoLuongPhongGoiDVChiTiet.setText(String.valueOf(host.getSoLuongPhong()));
                tvGiaGoiDVChiTiet.setText(String.valueOf(host.getGiaGoi()));

                if (host.getTrangThai() == 1)
                {
                    tvTrangThaiGoiDVChiTiet.setText("Da Khoa");
                    btnKhoaGoiDVChiTiet.setEnabled(false);
                    btnMoKhoaGoiDVChiTiet.setEnabled(true);
                }
                else
                {
                    tvTrangThaiGoiDVChiTiet.setText("Dang Hoat Dong");
                    btnKhoaGoiDVChiTiet.setEnabled(true);
                    btnMoKhoaGoiDVChiTiet.setEnabled(false);
                }

            }
            @Override
            public void onFailure(Call<GoiDichVu> call, Throwable t) {

            }
        });
    }
}