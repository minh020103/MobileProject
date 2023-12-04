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
import com.example.mobileproject.api.ApiFCMService;
import com.example.mobileproject.api.Const;
import com.example.mobileproject.api.admin.ApiServiceMinh;
import com.example.mobileproject.component.MFCM;
import com.example.mobileproject.datamodel.ChuTro;
import com.example.mobileproject.datamodel.FirebaseCloudMessaging;
import com.example.mobileproject.datamodel.ThongBao;
import com.example.mobileproject.datamodel.YeuCauXacThuc;
import com.example.mobileproject.datamodel.fcm.Notification;
import com.example.mobileproject.datamodel.fcm.PushNotification;

import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InfomationConfirmMotelRoomOwner extends AppCompatActivity {

    ImageView imgBack;

    LinearLayout llCall;
    LinearLayout llXacNhan;
    LinearLayout llHuy;

    ImageView imgMain;
    ImageView imgcccdT;
    ImageView imgcccdS;
    TextView tvTen;
    TextView tvSDT;
    TextView tvSTK;
    TextView tvTenNguoiThuHuong;

    private int idChuTro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_confirm_account_motel_room_owner_layout);

        //Lay du lieu tu fragment
        Intent intent = getIntent();
        idChuTro = intent.getIntExtra("idChuTro", 0);

        anhXa();

        setDataForUI(idChuTro);
        batSuKien();




    }

    @Override
    protected void onResume() {
        super.onResume();


    }

    private void batSuKien() {
        // bat su kien nut quay lai
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    private void batSuKienCanDuLieu(int idChuTro){
        //  bat su kien nut goi
        String phone = "tel:"+tvSDT.getText();
        llCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TAG", phone);
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse(phone));
                startActivity(callIntent);
            }
        });

        // Bat su kien khi bam xac nhan
        llXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiServiceMinh.apiService.xacThucThongTinChuTro(idChuTro).enqueue(new Callback<Integer>() {
                    @Override
                    public void onResponse(Call<Integer> call, Response<Integer> responseIdTaiKhoan) {
                        if (responseIdTaiKhoan.code() == 200) {
                            Log.d("TAG", "onResponse: idTaiKhoan"+responseIdTaiKhoan.body());
                            ApiServiceMinh.apiService.layTatCaTokenCuaTaiKhoan(responseIdTaiKhoan.body()).enqueue(new Callback<List<FirebaseCloudMessaging>>() {
                                @Override
                                public void onResponse(Call<List<FirebaseCloudMessaging>> call, Response<List<FirebaseCloudMessaging>> responseToken) {
                                    for (FirebaseCloudMessaging firebaseCloudMessaging:
                                         responseToken.body()) {
                                        // TODO: Tạo thông báo gửi kết quả cho người dùng
                                        MFCM.sendNotificationForAccountID(responseIdTaiKhoan.body(), new Date().getSeconds()+responseIdTaiKhoan.body(), "Xác thực tài khoản thành công", "Xác thực yêu cầu đăng ký tài khoản thành công cảm ơn bạn đã sử dụng dịch vụ");
                                    }
                                    finish();
                                }

                                @Override
                                public void onFailure(Call<List<FirebaseCloudMessaging>> call, Throwable t) {

                                }
                            });
                        }

                    }

                    @Override
                    public void onFailure(Call<Integer> call, Throwable t) {
                        Log.d("TAG", "onFailure Xac thuc tai khoan");
                    }
                });
                ApiServiceMinh.apiService.xacThucChuTro(idChuTro).enqueue(new Callback<Integer>() {
                    @Override
                    public void onResponse(Call<Integer> call, Response<Integer> response) {
                        finish();
                    }

                    @Override
                    public void onFailure(Call<Integer> call, Throwable t) {
                        Log.d("TAG", "felllllllllll");
                    }
                });
            }
        });

        llHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiServiceMinh.apiService.xoaYeuCauXacThucCuaChuTro(idChuTro).enqueue(new Callback<Integer>() {
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

    private void anhXa() {
        imgBack = findViewById(R.id.imgBack);


        tvSDT = findViewById(R.id.tvSDT);
        imgMain = findViewById(R.id.imgMain);
        imgcccdT = findViewById(R.id.imgcccdMT);
        imgcccdS = findViewById(R.id.imgcccdMS);
        tvTen = findViewById(R.id.tvTen);
        tvSTK = findViewById(R.id.tvSTK);
        tvTenNguoiThuHuong = findViewById(R.id.tvTenNguoiThuHuong);

        llCall = findViewById(R.id.llCall);
        llXacNhan = findViewById(R.id.llXacNhan);
        llHuy = findViewById(R.id.llHuy);
    }

    private void setDataForUI(int idChuTro) {
        ApiServiceMinh.apiService.layThongTinChiTietYeuCauXacThuc(idChuTro).enqueue(new Callback<YeuCauXacThuc>() {
            @Override
            public void onResponse(Call<YeuCauXacThuc> call, Response<YeuCauXacThuc> response) {
                if (response.code() == 200){
                    YeuCauXacThuc yeuCauXacThuc = response.body();
                    tvTen.setText(yeuCauXacThuc.getChuTro().getTen());
                    tvSDT.setText(yeuCauXacThuc.getChuTro().getSoDienThoai());
                    tvSTK.setText(yeuCauXacThuc.getChuTro().getTenChuTaiKhoanNganHang());
                    tvTenNguoiThuHuong.setText(yeuCauXacThuc.getChuTro().getTenChuTaiKhoanNganHang());
                    Glide.with(getApplicationContext()).load(Const.DOMAIN+yeuCauXacThuc.getChuTro().getHinh()).into(imgMain);
                    Glide.with(getApplicationContext()).load(Const.DOMAIN+yeuCauXacThuc.getCccdMatTruoc()).into(imgcccdT);
                    Glide.with(getApplicationContext()).load(Const.DOMAIN+yeuCauXacThuc.getCccdMatSau()).into(imgcccdS);

                    batSuKienCanDuLieu(idChuTro);
                }
            }

            @Override
            public void onFailure(Call<YeuCauXacThuc> call, Throwable t) {

            }
        });
    }
}