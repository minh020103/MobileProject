package com.example.mobileproject.activity.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mobileproject.R;
import com.example.mobileproject.api.Const;
import com.example.mobileproject.api.admin.ApiServiceMinh;
import com.example.mobileproject.datamodel.YeuCauDangKyGoi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InfomationPackageRegisterActivity extends AppCompatActivity {

    ImageView imgBack;
    LinearLayout llCall;
    LinearLayout llXacNhan;
    LinearLayout llHuy;

    private int idYeuCauDangKyGoi;
    TextView tvSDT;
    TextView tvTen;
    TextView tvSoPhong;
    TextView tvIDGoi;
    TextView tvThoiGian;
    TextView tvGia;
    ImageView imgCK;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_infomation_package_register_layout);


        Intent intent = getIntent();
        idYeuCauDangKyGoi = intent.getIntExtra("id", 0);

        imgBack = findViewById(R.id.imgBack);
        llCall = findViewById(R.id.llCall);
        llXacNhan = findViewById(R.id.llXacNhan);
        llHuy = findViewById(R.id.llHuy);

        tvSDT = findViewById(R.id.tvSDT);
        tvTen = findViewById(R.id.tvTen);
        tvSoPhong = findViewById(R.id.tvSoPhong);
        tvIDGoi = findViewById(R.id.tvIDGoi);
        tvThoiGian = findViewById(R.id.tvThoiGian);
        tvGia =  findViewById(R.id.tvGia);
        imgCK = findViewById(R.id.imgCK);

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        getDataFromAPI();

        String phone = "tel:"+tvSDT.getText();

        llCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse(phone));
                startActivity(callIntent);
            }
        });
    }


    private void getDataFromAPI() {
        ApiServiceMinh.apiService.layChiTietYeuCauDangKyGoi(idYeuCauDangKyGoi).enqueue(new Callback<YeuCauDangKyGoi>() {
            @Override
            public void onResponse(Call<YeuCauDangKyGoi> call, Response<YeuCauDangKyGoi> response) {
                tvTen.setText(response.body().getChuTro().getTen());
                tvSDT.setText(response.body().getChuTro().getSoDienThoai());
                tvIDGoi.setText("#" + response.body().getGoi().getId());
                tvTen.setText(response.body().getChuTro().getTen());
                tvSoPhong.setText(response.body().getGoi().getSoLuongPhongToiDa()+"");
                tvThoiGian.setText(response.body().getGoi().getThoiHan()+"");
                tvGia.setText(response.body().getGoi().getThoiHan()+"");
                Glide.with(getLayoutInflater().getContext()).load(Const.DOMAIN+response.body().getHinhAnhChuyenKhoan()).into(imgCK);
                batSuKienCanDuLieu(response.body().getId());
            }

            @Override
            public void onFailure(Call<YeuCauDangKyGoi> call, Throwable t) {

            }
        });
    }
    private void batSuKienCanDuLieu(int id){
        llXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiServiceMinh.apiService.xacNhanYeuCauDangKy(id).enqueue(new Callback<Integer>() {
                    @Override
                    public void onResponse(Call<Integer> call, Response<Integer> response) {
                        finish();
                    }

                    @Override
                    public void onFailure(Call<Integer> call, Throwable t) {

                    }
                });
            }
        });
        llHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiServiceMinh.apiService.huyYeuCauDangKyGoi(id).enqueue(new Callback<Integer>() {
                    @Override
                    public void onResponse(Call<Integer> call, Response<Integer> response) {
                        finish();
                    }

                    @Override
                    public void onFailure(Call<Integer> call, Throwable t) {

                    }
                });
            }
        });
    }
}