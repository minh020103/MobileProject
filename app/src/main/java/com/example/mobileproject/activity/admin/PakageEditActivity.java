package com.example.mobileproject.activity.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pakage_edit_layout);

        imgBackGoiDVSua = findViewById(R.id.imgBackGoiDVSua);
        edtThoiHanGoiDVSua = findViewById(R.id.edtThoiHanGoiDVSua);
        edtSoLuongPhongGoiDVSua = findViewById(R.id.edtSoLuongPhongGoiDVSua);
        edtGiaGoiDVSua = findViewById(R.id.edtGiaGoiDVSua);

        imgBackGoiDVSua.setOnClickListener(new View.OnClickListener() {
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

                edtThoiHanGoiDVSua.setText(String.valueOf(host.getThoiHan()));
                edtSoLuongPhongGoiDVSua.setText(String.valueOf(host.getSoLuongPhong()));
                edtGiaGoiDVSua.setText(String.valueOf(host.getGiaGoi()));

            }
            @Override
            public void onFailure(Call<GoiDichVu> call, Throwable t) {

            }
        });
    }
}