package com.example.mobileproject.activity.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.example.mobileproject.R;
import com.example.mobileproject.activity.admin.AdminActivity;
import com.example.mobileproject.api.Const;

public class SplashActivity extends AppCompatActivity {

    int idTaiKhoan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity_layout);
        SharedPreferences sharedPreferences = getSharedPreferences(Const.PRE_LOGIN, Context.MODE_PRIVATE);
        idTaiKhoan = sharedPreferences.getInt("idTaiKhoan", -1);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (idTaiKhoan == -1) {
                    Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(intent);
                }else {
                    Intent intent = new Intent(SplashActivity.this, AdminActivity.class);
                    startActivity(intent);
                }
                finish();
            }
        },3000);
    }
}