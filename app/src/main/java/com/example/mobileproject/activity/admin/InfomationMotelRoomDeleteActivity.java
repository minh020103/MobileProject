package com.example.mobileproject.activity.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mobileproject.R;
import com.example.mobileproject.api.Const;
import com.example.mobileproject.api.admin.ApiServiceMinh;
import com.example.mobileproject.datamodel.YeuCauXoaPhong;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InfomationMotelRoomDeleteActivity extends AppCompatActivity {

    ImageView imgBack, imgMain;
    LinearLayout llCall, llXacNhan, llHuy;

    private int id;
    TextView tvTen;
    TextView tvSDT, tvSoPhong, tvIdPhong, tvLyDo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_infomation_motel_room_delete_layout);

        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);

        imgBack = findViewById(R.id.imgBack);
        imgMain = findViewById(R.id.imgMain);

        llCall = findViewById(R.id.llCall);
        llXacNhan = findViewById(R.id.llXacNhan);
        llHuy = findViewById(R.id.llHuy);

        tvTen = findViewById(R.id.tvTen);
        tvSDT = findViewById(R.id.tvSDT);
        tvSoPhong = findViewById(R.id.tvSoPhong);
        tvIdPhong = findViewById(R.id.tvIdPhong);
        tvLyDo = findViewById(R.id.tvLyDo);


        getDataFromAPI();


        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void batSuKienCanDuLieu(int idPhong) {



        String phone = "tel:"+tvSDT.getText();

        llCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse(phone));
                startActivity(callIntent);
            }
        });

        llXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiServiceMinh.apiService.xacNhanYeuCauXoaPhong(idPhong).enqueue(new Callback<Integer>() {
                    @Override
                    public void onResponse(Call<Integer> call, Response<Integer> response) {

                    }

                    @Override
                    public void onFailure(Call<Integer> call, Throwable t) {

                    }
                });
                ApiServiceMinh.apiService.huyYeuCauXoaPhong(id).enqueue(new Callback<Integer>() {
                    @Override
                    public void onResponse(Call<Integer> call, Response<Integer> response) {

                    }

                    @Override
                    public void onFailure(Call<Integer> call, Throwable t) {

                    }
                });
                finish();
            }
        });

        llHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiServiceMinh.apiService.huyYeuCauXoaPhong(id).enqueue(new Callback<Integer>() {
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

    private void getDataFromAPI() {
        ApiServiceMinh.apiService.chiTietYeuCauXoaPhong(id).enqueue(new Callback<YeuCauXoaPhong>() {
            @Override
            public void onResponse(Call<YeuCauXoaPhong> call, Response<YeuCauXoaPhong> response) {
                Log.d("TAG", "okkk: "+response.code());
                if (response.code() == 200){
                    Log.d("TAG", "okkk: "+response.isSuccessful());
                    YeuCauXoaPhong yeuCauXoaPhong = response.body();
                    if (yeuCauXoaPhong != null) {
                        if (yeuCauXoaPhong.getChuTro() != null) {
                            Glide.with(getLayoutInflater().getContext()).load(Const.DOMAIN+yeuCauXoaPhong.getChuTro().getHinh()).into(imgMain);
                            tvTen.setText(yeuCauXoaPhong.getChuTro().getTen()+"");
                            tvSDT.setText(yeuCauXoaPhong.getChuTro().getSoDienThoai()+"");
                        }
                        if (yeuCauXoaPhong.getPhongTro() != null) {
                            tvSoPhong.setText(yeuCauXoaPhong.getPhongTro().getSoPhong() + "");
                            tvIdPhong.setText(yeuCauXoaPhong.getPhongTro().getId() + "");

                        }
                        tvLyDo.setText(yeuCauXoaPhong.getLyDo());
                    }
                    batSuKienCanDuLieu(yeuCauXoaPhong.getIdPhong());
                }
            }

            @Override
            public void onFailure(Call<YeuCauXoaPhong> call, Throwable t) {
                Log.d("TAG", "onFailure: "+t);
            }
        });
    }
}