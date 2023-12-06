package com.example.mobileproject.activity.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mobileproject.R;
import com.example.mobileproject.api.Const;
import com.example.mobileproject.api.admin.ApiServiceKiet;
import com.example.mobileproject.appuntil.AppUntil;
import com.example.mobileproject.datamodel.ChuTro;
import com.example.mobileproject.datamodel.Goi;
import com.example.mobileproject.datamodel.TaiKhoan;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MotelRoomOwnerDetailActivity extends AppCompatActivity {
    ImageView imgChuTroChiTiet;
    TextView tvTenChuTroChiTiet;
    TextView tvSoDienThoaiChuTroChiTiet;
    TextView tvTenTaiKhoanChuTroChiTiet;
    TextView tvIdDichVuChuTroChiTiet;
    TextView tvSoTaiKhoanNganHangChuTroChiTiet;
    TextView tvTenChuTaiKhoanNganHangChuTroChiTiet;
    TextView tvXacThucChuTroChiTiet;
    TextView tvTrangThaiChuTroChiTiet;
    ImageView imgCccdMatTruocChuTroChiTiet;
    ImageView imgCccdMatSauChuTroChiTiet;
    Button btnDanhSachPhongChuTroChiTiet;
    Button btnGoiDienChuTroChiTiet;
    Button btnKhoaTaiKhoanChuTroChiTiet;
    ImageView imgBackChuTroFragment;

    int idTaiKhoan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.motel_room_owner_detail_layout);

        Intent intent = getIntent();
        idTaiKhoan = intent.getIntExtra("idTaiKhoan", 0);

        imgChuTroChiTiet = findViewById(R.id.imgChuTroChiTiet);
        tvTenChuTroChiTiet = findViewById(R.id.tvTenChuTroChiTiet);
        tvSoDienThoaiChuTroChiTiet = findViewById(R.id.tvSDTChuTroChiTiet);
        tvTenTaiKhoanChuTroChiTiet = findViewById(R.id.tvTenTaiKhoanChuTroChiTiet);
        tvIdDichVuChuTroChiTiet = findViewById(R.id.tvIdDichVuChuTroChiTiet);
        tvSoTaiKhoanNganHangChuTroChiTiet = findViewById(R.id.tvSoTaiKhoanChuTroChiTiet);
        tvTenChuTaiKhoanNganHangChuTroChiTiet = findViewById(R.id.tvTenTaiKhoanNganHangChuTroChiTiet);
        tvXacThucChuTroChiTiet = findViewById(R.id.tvXacThucChuTroChiTiet);
        tvTrangThaiChuTroChiTiet = findViewById(R.id.tvTrangThaiChuTroChiTiet);
        imgCccdMatTruocChuTroChiTiet = findViewById(R.id.cccdMatTruocChuTroChiTiet);
        imgCccdMatSauChuTroChiTiet = findViewById(R.id.cccdMatSauChuTroChiTiet);

        btnGoiDienChuTroChiTiet = findViewById(R.id.btnGoiDienChuTro);
        btnKhoaTaiKhoanChuTroChiTiet = findViewById(R.id.btnKhoaTaiKhoanChuTro);

        imgBackChuTroFragment = findViewById(R.id.imgBackChuTroFragment);

        imgBackChuTroFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        HostByIdApi(AppUntil.ID_CHU_TRO);


        btnKhoaTaiKhoanChuTroChiTiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialogChangeStatusAccount();
            }
        });

        btnGoiDienChuTroChiTiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = "tel:"+tvSoDienThoaiChuTroChiTiet.getText();
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse(phone));
                startActivity(callIntent);
            }
        });

    }

    private void PakageById(int key)
    {
        ApiServiceKiet.apiServiceKiet.getPakageByIdAPI(key).enqueue(new Callback<Goi>() {
            @Override
            public void onResponse(Call<Goi> call, Response<Goi> response) {
                Goi goi = response.body();
                String thoiHan = String.valueOf(goi.getThoiHan());
                String soPhong = String.valueOf(goi.getSoLuongPhongToiDa());
                tvIdDichVuChuTroChiTiet.setText(thoiHan + " ngày / " + soPhong + " phòng");

            }

            @Override
            public void onFailure(Call<Goi> call, Throwable t) {

            }
        });
    }

    private void HostByIdApi(int key)
    {
        ApiServiceKiet.apiServiceKiet.getHostByIdAPI(key).enqueue(new Callback<ChuTro>() {
            @Override
            public void onResponse(Call<ChuTro> call, Response<ChuTro> response) {
                ChuTro host = response.body();

                Glide.with(getApplicationContext()).load(Const.DOMAIN + host.getHinh()).into(imgChuTroChiTiet);
                tvTenChuTroChiTiet.setText(host.getTen());
                tvSoDienThoaiChuTroChiTiet.setText(host.getSoDienThoai());
                //tvIdDichVuChuTroChiTiet.setText(String.valueOf(host.getIdGoi()));
                PakageById(host.getIdGoi());
                tvSoTaiKhoanNganHangChuTroChiTiet.setText(host.getSoTaiKhoanNganHang());
                tvTenChuTaiKhoanNganHangChuTroChiTiet.setText(host.getTenChuTaiKhoanNganHang());
                if (host.getYeuCauXacThuc() != null)
                {
                    if (host.getYeuCauXacThuc().getCccdMatTruoc() != null)
                    {
                        Glide.with(getApplicationContext()).load(Const.DOMAIN +  host.getYeuCauXacThuc().getCccdMatTruoc()).into(imgCccdMatTruocChuTroChiTiet);
                    }
                    else
                    {
                        imgCccdMatTruocChuTroChiTiet.setImageResource(R.drawable.not_image);
                    }
                    if (host.getYeuCauXacThuc().getCccdMatSau() != null)
                    {
                        Glide.with(getApplicationContext()).load(Const.DOMAIN +  host.getYeuCauXacThuc().getCccdMatSau()).into(imgCccdMatSauChuTroChiTiet);

                    }
                    else
                    {
                        imgCccdMatSauChuTroChiTiet.setImageResource(R.drawable.not_image);
                    }
                }
                else
                {
                    imgCccdMatTruocChuTroChiTiet.setImageResource(R.drawable.not_image);
                    imgCccdMatSauChuTroChiTiet.setImageResource(R.drawable.not_image);

                }

                if (host.getXacThuc() == 1)
                {
                    tvXacThucChuTroChiTiet.setText("Đã xác thực");
                    tvXacThucChuTroChiTiet.setTextColor(0xFF00FF00);
                }
                else
                {
                    tvXacThucChuTroChiTiet.setText("Chưa xác thực");
                    tvXacThucChuTroChiTiet.setTextColor(0xFFFF0000);
                }
                if (host.getTaiKhoan().getTrangThai() == 1)
                {
                    tvTrangThaiChuTroChiTiet.setText("Đã khóa");
                    tvTrangThaiChuTroChiTiet.setTextColor(0xFFFF0000);
                    //blue
                    btnKhoaTaiKhoanChuTroChiTiet.setBackgroundColor(Color.parseColor("#1218db"));
                    btnKhoaTaiKhoanChuTroChiTiet.setText("Mở khóa tài khoản");
                }
                else
                {
                    tvTrangThaiChuTroChiTiet.setText("Đang hoạt động");
                    tvTrangThaiChuTroChiTiet.setTextColor(0xFF00FF00);
                    //red
                    btnKhoaTaiKhoanChuTroChiTiet.setBackgroundColor(Color.parseColor("#fa1302"));
                    btnKhoaTaiKhoanChuTroChiTiet.setText("Khóa tài khoản");
                }
                AppUntil.TEN_CHU_TRO = host.getTen();

            }
            @Override
            public void onFailure(Call<ChuTro> call, Throwable t) {

            }
        });
    }

    private void thayDoiTrangThaiTaiKhoan()
    {
        Call<Integer> call = ApiServiceKiet.apiServiceKiet.thayDoiTrangThaiTaiKhoan(idTaiKhoan);
        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
//            AlertDialog.Builder builder = new AlertDialog.Builder(MotelRoomOwnerDetailActivity.this);
//            builder.setMessage(call.request().toString()).setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//
//                }
//            });
//            builder.create();
//            builder.show();
            }
            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Toast.makeText(MotelRoomOwnerDetailActivity.this, "Loi", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void openDialogChangeStatusAccount()
    {
        new AlertDialog.Builder(this).setMessage("Thay đổi trạng thái tài khoản '" + AppUntil.TEN_CHU_TRO + "' ?").setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.d("TAG", "id: " + AppUntil.ID_CHU_TRO);
                thayDoiTrangThaiTaiKhoan();
                HostByIdApi(AppUntil.ID_CHU_TRO);
            }
        }).setNegativeButton("Cancle",null).show();
    }


}