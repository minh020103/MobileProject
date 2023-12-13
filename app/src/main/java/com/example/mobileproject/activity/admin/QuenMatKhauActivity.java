package com.example.mobileproject.activity.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.mobileproject.R;
import com.example.mobileproject.api.admin.ApiServiceMinh;
import com.example.mobileproject.datamodel.ResultForgotPassword;
import com.example.mobileproject.datamodel.TaiKhoan;
import com.google.android.material.button.MaterialButton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuenMatKhauActivity extends AppCompatActivity {

    private EditText edtUsername;
    private TextView tvNotification, tvLogin;
    private MaterialButton btnGuiMaXacNhan;
    private ProgressBar loding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quen_mat_khau_layout);
        //Ánh xạ
        // EditText
        edtUsername = findViewById(R.id.edt_username);
        // TextView
        tvNotification = findViewById(R.id.tvNotification);
        tvLogin = findViewById(R.id.tvLogin);
        // Button
        btnGuiMaXacNhan = findViewById(R.id.btnSend);

        //ProgressBar
        loding = findViewById(R.id.loading);

        btnGuiMaXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loding.setVisibility(View.VISIBLE);
                String username = edtUsername.getText().toString();
                Log.d("TAGL", "onClick: 1");
                ApiServiceMinh.apiService.layTaiKhoanTheoUsername(username).enqueue(new Callback<TaiKhoan>() {
                    @Override
                    public void onResponse(Call<TaiKhoan> call, Response<TaiKhoan> responseTaiKhoan) {
                        if (responseTaiKhoan.code() == 200) {
                            Log.d("TAGL", "onClick: 2");
                            if (responseTaiKhoan.body() != null) {
                                ApiServiceMinh.apiService.sendEmailForgotPassword(responseTaiKhoan.body().getId(), responseTaiKhoan.body().getEmail()).enqueue(new Callback<ResultForgotPassword>() {
                                    @Override
                                    public void onResponse(Call<ResultForgotPassword> call, Response<ResultForgotPassword> responseResult) {
                                        if (responseResult.code() == 200){
                                            Log.d("TAGL", "onClick: 3");
                                            if (responseResult.body() != null){
                                                if (responseResult.body().getStatus() == 1) {
                                                    Log.d("TAGL", "onClick: 4");
                                                    Intent intent = new Intent(QuenMatKhauActivity.this, LayLaiMatKhauActivity.class);
                                                    intent.putExtra("idTaiKhoan", responseTaiKhoan.body().getId());
                                                    startActivity(intent);
                                                    finish();

                                                }
                                                else {
                                                    Log.d("TAGL", "onClick: 5");
                                                    alertDialog(responseResult.body().getMessage());
                                                }
                                            }
                                        }

                                        loding.setVisibility(View.GONE);
                                    }

                                    @Override
                                    public void onFailure(Call<ResultForgotPassword> call, Throwable t) {
                                        Log.d("TAGL", "onClick: 6");
                                    }
                                });
                            } else {
                                Log.d("TAGL", "onClick: 7");
                                tvNotification.setText("Tài khoản không chính sác");
                                tvNotification.setVisibility(View.VISIBLE);
                                loding.setVisibility(View.GONE);
                            }


                        }
                    }

                    @Override
                    public void onFailure(Call<TaiKhoan> call, Throwable t) {
                        alertDialog("Tài khoản không tồn tại");
                        loding.setVisibility(View.GONE);
                    }
                });
            }
        });
        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void alertDialog(String s) {
        new android.app.AlertDialog.Builder(this)
                .setTitle("Thông báo")
                .setMessage(s)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
//                .setNegativeButton("Cancal", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        dialogInterface.dismiss();
//                    }
//                })
                .show();
    }
}