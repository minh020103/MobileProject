package com.example.mobileproject.activity.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mobileproject.R;
import com.example.mobileproject.api.ApiServiceKiet;
import com.example.mobileproject.appuntil.AppUntil;
import com.example.mobileproject.datamodel.ChuTro;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MotelRoomOwnerDetailActivity extends AppCompatActivity {
    ImageView imgChuTroChiTiet;
    TextView tvTenChuTroChiTiet;
    TextView tvSoDienThoaiChuTroChiTiet;
    TextView tvGioiTinhChuTroChiTiet;
    TextView tvIdDichVuChuTroChiTiet;
    TextView tvSoTaiKhoanNganHangChuTroChiTiet;
    TextView tvTenChuTaiKhoanNganHangChuTroChiTiet;
    TextView tvTrangThaiChuTroChiTiet;
    Button btnDanhSachPhongChuTroChiTiet;
    Button btnGoiDienChuTroChiTiet;
    Button btnKhoaTaiKhoanChuTroChiTiet;
    Button btnMoTaiKhoanChuTroChiTiet;

    ImageView imgBackChuTroFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.motel_room_owner_detail_layout);

        imgChuTroChiTiet = findViewById(R.id.imgChuTroChiTiet);
        tvTenChuTroChiTiet = findViewById(R.id.tvTenChuTroChiTiet);
        tvSoDienThoaiChuTroChiTiet = findViewById(R.id.tvSDTChuTroChiTiet);
        tvGioiTinhChuTroChiTiet = findViewById(R.id.tvGioiTinhChuTroChiTiet);
        tvIdDichVuChuTroChiTiet = findViewById(R.id.tvIdDichVuChuTroChiTiet);
        tvSoTaiKhoanNganHangChuTroChiTiet = findViewById(R.id.tvSoTaiKhoanChuTroChiTiet);
        tvTenChuTaiKhoanNganHangChuTroChiTiet = findViewById(R.id.tvTenTaiKhoanNganHangChuTroChiTiet);
        tvTrangThaiChuTroChiTiet = findViewById(R.id.tvTrangThaiChuTroChiTiet);

        btnDanhSachPhongChuTroChiTiet = findViewById(R.id.btnDanhSachPhongChuTro);
        btnGoiDienChuTroChiTiet = findViewById(R.id.btnGoiDienChuTro);
        btnKhoaTaiKhoanChuTroChiTiet = findViewById(R.id.btnKhoaTaiKhoanChuTro);
        btnMoTaiKhoanChuTroChiTiet = findViewById(R.id.btnMoTaiKhoanChuTro);

        imgBackChuTroFragment = findViewById(R.id.imgBackChuTroChiTiet);

        imgBackChuTroFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        HostByIdApi(AppUntil.ID_CHU_TRO);
        btnGoiDienChuTroChiTiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = "tel:"+tvSoDienThoaiChuTroChiTiet.getText();
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse(phone));
                startActivity(callIntent);
            }
        });
        btnMoTaiKhoanChuTroChiTiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialogConfirmUnLockAccount();
            }
        });
        btnKhoaTaiKhoanChuTroChiTiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialogConfirmLockAccount();
            }
        });

    }

    private void HostByIdApi(int key)
    {
        ApiServiceKiet.apiServiceKiet.getHostByIdAPI(key).enqueue(new Callback<ChuTro>() {
            @Override
            public void onResponse(Call<ChuTro> call, Response<ChuTro> response) {
                ChuTro host = response.body();

                Glide.with(getApplicationContext()).load(host.getHinhNguoiDung()).into(imgChuTroChiTiet);
                tvTenChuTroChiTiet.setText(host.getTenNguoiDung());
                tvSoDienThoaiChuTroChiTiet.setText(host.getSoDienThoai());
                tvGioiTinhChuTroChiTiet.setText(host.getGioiTinh());
                tvIdDichVuChuTroChiTiet.setText(String.valueOf(host.getIdDichVu()));
                tvSoTaiKhoanNganHangChuTroChiTiet.setText(host.getSoTaiKhoanNganHang());
                tvTenChuTaiKhoanNganHangChuTroChiTiet.setText(host.getTenChuTaiKhoanNganHang());
                if (host.getXacThuc() == 1)
                {
                    tvTrangThaiChuTroChiTiet.setText("Tai khoan da khoa");
                    tvTrangThaiChuTroChiTiet.setTextColor(0xFFFF0000);
                    btnKhoaTaiKhoanChuTroChiTiet.setEnabled(false);
                    btnMoTaiKhoanChuTroChiTiet.setEnabled(true);
                }
                else
                {
                    tvTrangThaiChuTroChiTiet.setText("Dang hoat dong");
                    tvTrangThaiChuTroChiTiet.setTextColor(0xFF00FF00);
                    btnKhoaTaiKhoanChuTroChiTiet.setEnabled(true);
                    btnMoTaiKhoanChuTroChiTiet.setEnabled(false);
                }
                AppUntil.TEN_CHU_TRO = host.getTenNguoiDung();

            }
            @Override
            public void onFailure(Call<ChuTro> call, Throwable t) {

            }
        });
    }

    private void LockHostApi(int key)
    {
        ApiServiceKiet.apiServiceKiet.getHostLockAccountAPI(key).enqueue(new Callback<ChuTro>() {
            @Override
            public void onResponse(Call<ChuTro> call, Response<ChuTro> response) {
            }
            @Override
            public void onFailure(Call<ChuTro> call, Throwable t) {
            }
        });
    }

    private void UnLockHostApi(int key)
    {
        ApiServiceKiet.apiServiceKiet.getHostUnLockAccountAPI(key).enqueue(new Callback<ChuTro>() {
            @Override
            public void onResponse(Call<ChuTro> call, Response<ChuTro> response) {
            }
            @Override
            public void onFailure(Call<ChuTro> call, Throwable t) {
            }
        });
    }

    private void openDialogConfirmLockAccount()
    {
        new AlertDialog.Builder(this).setMessage("Khoa tai khoan '" + AppUntil.TEN_CHU_TRO + "'").setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                LockHostApi(AppUntil.ID_CHU_TRO);
//                btnMoTaiKhoanChuTroChiTiet.setEnabled(true);
//                btnKhoaTaiKhoanChuTroChiTiet.setEnabled(false);
                HostByIdApi(AppUntil.ID_CHU_TRO);

            }
        }).setNegativeButton("Cancle",null).show();
    }

    private void openDialogConfirmUnLockAccount()
    {
        new AlertDialog.Builder(this).setMessage("Mo khoa tai khoan '" + AppUntil.TEN_CHU_TRO + "'").setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                UnLockHostApi(AppUntil.ID_CHU_TRO);
//                btnMoTaiKhoanChuTroChiTiet.setEnabled(false);
//                btnKhoaTaiKhoanChuTroChiTiet.setEnabled(true);
                HostByIdApi(AppUntil.ID_CHU_TRO);

            }
        }).setNegativeButton("Cancle",null).show();
    }
}