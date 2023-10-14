package com.example.mobileproject.activity.admin;

import android.app.AlertDialog;
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
import com.example.mobileproject.datamodel.TaiKhoan;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditPasswordAdminActivity extends AppCompatActivity {
    AppCompatImageView ic_back;
    EditText matKhauMoi, matKhauHienTai, xacNhanMKMoi;
    Button btnXacNhanDoiMK;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_password_admin);
        anhXa();

        batSuKienButton();

    }

    private void batSuKienButton(){
        ic_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnXacNhanDoiMK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(kiemTra(matKhauHienTai,matKhauMoi,xacNhanMKMoi)){
                    layTaiKhoanXuong(matKhauHienTai,matKhauMoi,xacNhanMKMoi);
                }
            }
        });
    }

    private void layTaiKhoanXuong(EditText matKhauHienTai, EditText matKhauMoi, EditText xacNhanMKMoi){
        Call<TaiKhoan> call = ApiServiceNghiem.apiService.layTaiKhoanXuong(1);
        call.enqueue(new Callback<TaiKhoan>() {
            @Override
            public void onResponse(Call<TaiKhoan> call, Response<TaiKhoan> response) {
                    if(kiemTraMKHienTai(matKhauHienTai,response.body().getMatKhau())){
                        /// Tiến Hành Đổi Mật Khẩu ở đây
                    }else{
                        Toast.makeText(getApplicationContext(),"Mật Khẩu Không Giống", Toast.LENGTH_SHORT).show();
                    }
            }

            @Override
            public void onFailure(Call<TaiKhoan> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Mật Khẩu Không Thể Load", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean kiemTraMKHienTai(EditText matKhauHienTai, String matKhauHientai){
        if(matKhauHienTai.getText().toString().equals(matKhauHientai)){
            return true;
        }
        return false;
    }

    private void thongBao(String message){
        AlertDialog.Builder builder = new AlertDialog.Builder()
    }

    private boolean kiemTra(EditText matKhauHienTai, EditText matKhauMoi, EditText xacNhanMKMoi){
        if(!matKhauHienTai.getText().toString().isEmpty()&&!matKhauMoi.getText().toString().isEmpty()&&!xacNhanMKMoi.getText().toString().isEmpty()){
            return true;
        }
        return false;
    }
    private void anhXa(){
        ic_back = findViewById(R.id.icon_back);
        matKhauHienTai = findViewById(R.id.matKhauHienTai);
        matKhauMoi = findViewById(R.id.matKhauMoi);
        xacNhanMKMoi = findViewById(R.id.xacNhanMKMoi);
        btnXacNhanDoiMK = findViewById(R.id.btnXacNhanDoiMK);

    }
}
