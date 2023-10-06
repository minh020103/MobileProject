package com.example.mobileproject.activity.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
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
    ImageView imgHost;
    TextView tvTenHost;
    TextView tvSoDienThoaiHost;
    TextView tvGioiTinhHost;
    TextView tvIdDichVuHost;
    TextView tvSoTaiKhoanNganHangHost;
    TextView tvTenChuTaiKhoanNganHangHost;
    TextView tvTrangThaiHost;

    Button btnHostDanhSachPhong;
    Button btnHostCall;
    Button btnHostKhoaTaiKhoan;
    Button btnHostMoTaiKhoan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.host_detail);

        imgHost = findViewById(R.id.imgHostDetail);
        tvTenHost = findViewById(R.id.tvTenHostDetail);
        tvSoDienThoaiHost = findViewById(R.id.tvSoDienThoaiHostDetail);
        tvGioiTinhHost = findViewById(R.id.tvGioiTinhHostDetail);
        tvIdDichVuHost = findViewById(R.id.tvIdDichVuHostDetail);
        tvSoTaiKhoanNganHangHost = findViewById(R.id.tvSoTaiKhoanNganHangHostDetail);
        tvTenChuTaiKhoanNganHangHost = findViewById(R.id.tvTenTaiKhoanNganHangHostDetail);
        tvTrangThaiHost = findViewById(R.id.tvTrangThaiHostDetail);

        btnHostDanhSachPhong = findViewById(R.id.btnHostDanhSachPhong);
        btnHostCall = findViewById(R.id.btnHostCall);
        btnHostKhoaTaiKhoan = findViewById(R.id.btnHostKhoaTaiKhoan);
        btnHostMoTaiKhoan = findViewById(R.id.btnHostMoTaiKhoan);

        HostByIdApi(AppUtil.Id);

        btnHostKhoaTaiKhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialogConfirmLockAccount();
                //LockHostApi(AppUtil.Id);
            }
        });

        btnHostMoTaiKhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialogConfirmUnLockAccount();
                //UnLockHostApi(AppUtil.Id);
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
                tvTenHost.setText(host.getTenNguoiDung());
                tvSoDienThoaiHost.setText(host.getSoDienThoai());
                tvGioiTinhHost.setText(host.getGioiTinh());
                tvIdDichVuHost.setText(String.valueOf(host.getIdDichVu()));
                tvSoTaiKhoanNganHangHost.setText(String.valueOf(host.getSoTaiKhoanNganHang()));
                tvTenChuTaiKhoanNganHangHost.setText(host.getTenChuTaiKhoanNganHang());
                if (host.getXacThuc() == 1)
                {
                    tvTrangThaiHost.setText("Tai khoan da khoa");
                    btnHostKhoaTaiKhoan.setEnabled(false);
                    btnHostMoTaiKhoan.setEnabled(true);
                }
                else
                {
                    tvTrangThaiHost.setText("Dang hoat dong");
                    btnHostKhoaTaiKhoan.setEnabled(true);
                    btnHostMoTaiKhoan.setEnabled(false);
                }
                AppUtil.nameHost = host.getTenNguoiDung();

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

    private void openDialogConfirmLockAccount()
    {
        new AlertDialog.Builder(this).setMessage("Khoa tai khoan '" + AppUtil.nameHost + "'").setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                LockHostApi(AppUtil.Id);
            }
        }).setNegativeButton("Cancle",null).show();
    }

    private void openDialogConfirmUnLockAccount()
    {
        new AlertDialog.Builder(this).setMessage("Mo khoa tai khoan '" + AppUtil.nameHost + "'").setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                UnLockHostApi(AppUtil.Id);
            }
        }).setNegativeButton("Cancle",null).show();
    }
}