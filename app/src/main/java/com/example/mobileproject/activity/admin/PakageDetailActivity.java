package com.example.mobileproject.activity.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mobileproject.R;
import com.example.mobileproject.api.admin.ApiServiceKiet;
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

        btnSuaGoiDVChiTiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), PakageEditActivity.class);
                startActivity(intent);
            }
        });

        btnMoKhoaGoiDVChiTiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialogConfirmUnLockPakage();
            }
        });

        btnKhoaGoiDVChiTiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialogConfirmLockPakage();
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

    private void LockPakageApi(int id)
    {
        ApiServiceKiet.apiServiceKiet.lockPakageByIdAPI(id).enqueue(new Callback<GoiDichVu>() {
            @Override
            public void onResponse(Call<GoiDichVu> call, Response<GoiDichVu> response) {
            }
            @Override
            public void onFailure(Call<GoiDichVu> call, Throwable t) {
            }
        });
    }

    private void UnLockPakageApi(int key)
    {
        ApiServiceKiet.apiServiceKiet.unLockPakageByIdAPI(key).enqueue(new Callback<GoiDichVu>() {
            @Override
            public void onResponse(Call<GoiDichVu> call, Response<GoiDichVu> response) {
            }
            @Override
            public void onFailure(Call<GoiDichVu> call, Throwable t) {
            }
        });
    }

    private void openDialogConfirmLockPakage()
    {
        new AlertDialog.Builder(this).setMessage("Khoa tai khoan '" + AppUntil.TEN_CHU_TRO + "'").setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                LockPakageApi(AppUntil.ID_GOI_DICH_VU);
//                btnMoKhoaGoiDVChiTiet.setEnabled(true);
//                btnKhoaGoiDVChiTiet.setEnabled(false);
                PakageByIdAPI(AppUntil.ID_GOI_DICH_VU);

            }
        }).setNegativeButton("Cancle",null).show();
    }

    private void openDialogConfirmUnLockPakage()
    {
        new AlertDialog.Builder(this).setMessage("Mo khoa tai khoan '" + AppUntil.TEN_CHU_TRO + "'").setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                UnLockPakageApi(AppUntil.ID_GOI_DICH_VU);
//                btnMoKhoaGoiDVChiTiet.setEnabled(false);
//                btnKhoaGoiDVChiTiet.setEnabled(true);
                PakageByIdAPI(AppUntil.ID_GOI_DICH_VU);
            }
        }).setNegativeButton("Cancle",null).show();
    }
}