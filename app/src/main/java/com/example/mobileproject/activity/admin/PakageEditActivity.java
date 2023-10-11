package com.example.mobileproject.activity.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.mobileproject.R;
import com.example.mobileproject.api.admin.ApiServiceKiet;
import com.example.mobileproject.appuntil.AppUntil;
import com.example.mobileproject.datamodel.GoiDichVu;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PakageEditActivity extends AppCompatActivity {
    ImageView imgBackGoiDVSua;
    EditText edtThoiHanGoiDVSua;
    EditText edtSoLuongPhongGoiDVSua;
    EditText edtGiaGoiDVSua;

    Button btnSuaGoiDV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pakage_edit_layout);

        imgBackGoiDVSua = findViewById(R.id.imgBackGoiDVSua);
        edtThoiHanGoiDVSua = findViewById(R.id.edtThoiHanGoiDVSua);
        edtSoLuongPhongGoiDVSua = findViewById(R.id.edtSoLuongPhongGoiDVSua);
        edtGiaGoiDVSua = findViewById(R.id.edtGiaGoiDVSua);
        btnSuaGoiDV = findViewById(R.id.btnGoiDVSua);

        imgBackGoiDVSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnSuaGoiDV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int thoiHan = Integer.parseInt(edtThoiHanGoiDVSua.getText()+"");
                int soLuong = Integer.parseInt(edtSoLuongPhongGoiDVSua.getText()+"");
                int gia = Integer.parseInt(edtGiaGoiDVSua.getText()+"");

                openDialogConfirmUpdatePakage(thoiHan, soLuong, gia);
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

                edtThoiHanGoiDVSua.setText(String.valueOf(host.getThoiHan()));
                edtSoLuongPhongGoiDVSua.setText(String.valueOf(host.getSoLuongPhong()));
                edtGiaGoiDVSua.setText(String.valueOf(host.getGiaGoi()));

            }
            @Override
            public void onFailure(Call<GoiDichVu> call, Throwable t) {

            }
        });
    }

    private void UpdatePakageAPI(int thoiHan, int soLuongPhongToiDa, int gia)
    {
        ApiServiceKiet.apiServiceKiet.updatePakage(AppUntil.ID_GOI_DICH_VU,thoiHan,soLuongPhongToiDa,gia).enqueue(new Callback<GoiDichVu>() {
            @Override
            public void onResponse(Call<GoiDichVu> call, Response<GoiDichVu> response) {

            }
            @Override
            public void onFailure(Call<GoiDichVu> call, Throwable t) {

            }
        });
    }

    private void openDialogConfirmUpdatePakage(int i2, int i3, int i4)
    {
        new AlertDialog.Builder(this).setMessage("Xác nhận sửa gói dịch vụ số '" + AppUntil.ID_GOI_DICH_VU + "' ?").setCancelable(false).setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                UpdatePakageAPI(i2, i3, i4);
            }
        }).setNegativeButton("Hủy",null).show();
    }
}