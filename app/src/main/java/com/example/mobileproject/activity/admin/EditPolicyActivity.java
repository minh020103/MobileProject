package com.example.mobileproject.activity.admin;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import com.example.mobileproject.R;
import com.example.mobileproject.api.admin.ApiServiceNghiem;
import com.example.mobileproject.datamodel.ChinhSach;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditPolicyActivity extends AppCompatActivity {
    AppCompatImageView ic_back;


    EditText noiDungChinhSach;
    Button btnXacNhan;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_policy);
        anhXa();
        setDuLieu();
        ic_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void setDuLieu(){
        Call<ChinhSach> call=ApiServiceNghiem.apiService.layChinhSachXuong(1);
        call.enqueue(new Callback<ChinhSach>() {
            @Override
            public void onResponse(Call<ChinhSach> call, Response<ChinhSach> response) {
                noiDungChinhSach.setText(response.body().getNoiDungChinhSach());
                setSuKienCapNhat(noiDungChinhSach);
            }

            @Override
            public void onFailure(Call<ChinhSach> call, Throwable t) {
                thongBao("ERROR!");
            }
        });
    }

    private void thongBao(String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message).setPositiveButton("OKE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create();
        builder.show();
    }
    private void setSuKienCapNhat(EditText editText){
        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!editText.getText().toString().isEmpty()){
                    ChinhSach chinhSach = new ChinhSach(editText.getText().toString());
                    Call<Integer> call = ApiServiceNghiem.apiService.capNhatChinhSach(1,chinhSach);
                    call.enqueue(new Callback<Integer>() {
                        @Override
                        public void onResponse(Call<Integer> call, Response<Integer> response) {
                           thongBao("Cập Nhật Thành Công!");




                        }

                        @Override
                        public void onFailure(Call<Integer> call, Throwable t) {
                        thongBao("Lỗi!");
                        }
                    });
                }
            }
        });

    }


    private void anhXa(){
        ic_back = findViewById(R.id.icon_back);
        noiDungChinhSach = findViewById(R.id.noiDungCS);
        btnXacNhan= findViewById(R.id.btnXacNhanDoi);
    }
}
