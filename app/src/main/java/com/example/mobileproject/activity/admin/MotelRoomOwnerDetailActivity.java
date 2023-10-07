package com.example.mobileproject.activity.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
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
    ImageView imgChuTro;
    TextView tvTenChuTro;
    TextView tvSoDienThoaiChuTro;
    TextView tvGioiTinhChuTro;
    TextView tvIdDichVuChuTro;
    TextView tvSoTaiKhoanNganHangChuTro;
    TextView tvTenChuTaiKhoanNganHangChuTro;
    TextView tvTrangThaiChuTro;

    Button btnDanhSachPhongChuTro;
    Button btnGoiDienChuTro;
    Button btnKhoaTaiKhoanChuTro;
    Button btnMoTaiKhoanChuTro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.motel_room_owner_detail_layout);

        imgChuTro = findViewById(R.id.imgChuTroChiTiet);
        tvTenChuTro = findViewById(R.id.tvTenChuTroChiTiet);
        tvSoDienThoaiChuTro = findViewById(R.id.tvSDTChuTroChiTiet);
        tvGioiTinhChuTro = findViewById(R.id.tvGioiTinhChuTroChiTiet);
        tvIdDichVuChuTro = findViewById(R.id.tvIdDichVuChuTroChiTiet);
        tvSoTaiKhoanNganHangChuTro = findViewById(R.id.tvSoTaiKhoanChuTroChiTiet);
        tvTenChuTaiKhoanNganHangChuTro = findViewById(R.id.tvTenTaiKhoanNganHangChuTroChiTiet);
        tvTrangThaiChuTro = findViewById(R.id.tvTrangThaiChuTroChiTiet);

        btnDanhSachPhongChuTro = findViewById(R.id.btnDanhSachPhongChuTro);
        btnGoiDienChuTro = findViewById(R.id.btnGoiDienChuTro);
        btnKhoaTaiKhoanChuTro = findViewById(R.id.btnKhoaTaiKhoanChuTro);
        btnMoTaiKhoanChuTro = findViewById(R.id.btnMoTaiKhoanChuTro);
    }

    private void HostByIdApi(int key)
    {
        ApiServiceKiet.apiServiceKiet.getHostByIdAPI(key).enqueue(new Callback<ChuTro>() {
            @Override
            public void onResponse(Call<ChuTro> call, Response<ChuTro> response) {
                Log.d("tinnhan", "thanh cong");
                ChuTro host = (ChuTro) response.body();

                Glide.with(getApplicationContext()).load(host.getHinhNguoiDung()).into(imgChuTro);
                tvTenChuTro.setText(host.getTenNguoiDung());
                tvSoDienThoaiChuTro.setText(host.getSoDienThoai());
                tvGioiTinhChuTro.setText(host.getGioiTinh());
                tvIdDichVuChuTro.setText(String.valueOf(host.getIdDichVu()));
                tvSoTaiKhoanNganHangChuTro.setText(String.valueOf(host.getSoTaiKhoanNganHang()));
                tvTenChuTro.setText(host.getTenChuTaiKhoanNganHang());
                if (host.getXacThuc() == 1)
                {
                    tvTrangThaiChuTro.setText("Tai khoan da khoa");
                    btnKhoaTaiKhoanChuTro.setEnabled(false);
                    btnMoTaiKhoanChuTro.setEnabled(true);
                }
                else
                {
                    tvTrangThaiChuTro.setText("Dang hoat dong");
                    btnKhoaTaiKhoanChuTro.setEnabled(true);
                    btnMoTaiKhoanChuTro.setEnabled(false);
                }
                AppUntil.TEN_CHU_TRO = host.getTenNguoiDung();

            }

            @Override
            public void onFailure(Call<ChuTro> call, Throwable t) {
                Log.d("tinnhan", "that bai");

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
            }
        }).setNegativeButton("Cancle",null).show();
    }

    private void openDialogConfirmUnLockAccount()
    {
        new AlertDialog.Builder(this).setMessage("Mo khoa tai khoan '" + AppUntil.TEN_CHU_TRO + "'").setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                UnLockHostApi(AppUntil.ID_CHU_TRO);
            }
        }).setNegativeButton("Cancle",null).show();
    }
}