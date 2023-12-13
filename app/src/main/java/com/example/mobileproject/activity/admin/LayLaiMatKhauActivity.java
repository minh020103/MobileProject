package com.example.mobileproject.activity.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.mobileproject.R;
import com.example.mobileproject.activity.login.LoginActivity;
import com.example.mobileproject.api.admin.ApiServiceMinh;
import com.example.mobileproject.datamodel.ResultForgotPassword;
import com.google.android.material.button.MaterialButton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LayLaiMatKhauActivity extends AppCompatActivity {

    private int idTaiKhoan;
    private EditText edtCode, edtPassword;
    private TextView tvNotification, tvLogin;
    private MaterialButton btnCreatePassword;
    private ProgressBar loading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lay_lai_mat_khau_layout);
        Intent intent = getIntent();
        idTaiKhoan = intent.getIntExtra("idTaiKhoan", -1);
        edtCode = findViewById(R.id.edtCode);
        edtPassword = findViewById(R.id.edtPassword);
        tvNotification = findViewById(R.id.tvNotification);
        tvLogin = findViewById(R.id.tvLogin);
        btnCreatePassword = findViewById(R.id.btnCreatePassword);
        loading = findViewById(R.id.loading);
        btnCreatePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loading.setVisibility(View.VISIBLE);
                if (edtPassword.getText().toString().length() >= 6) {
                    ApiServiceMinh.apiService.resultForgotpassword(idTaiKhoan, Integer.parseInt(edtCode.getText().toString()), edtPassword.getText().toString()).enqueue(new Callback<ResultForgotPassword>() {
                        @Override
                        public void onResponse(Call<ResultForgotPassword> call, Response<ResultForgotPassword> response) {
                            if (response.code() == 200) {
                                if (response.body() != null) {
                                    if (response.body().getStatus() == 1) {
                                        alertDialog(response.body().getMessage());
                                    }
                                } else {
                                    alertDialog(response.body().getMessage());
                                    tvNotification.setText("Hãy kiểm tra lại code trong email và nhập đúng");
                                    tvNotification.setVisibility(View.VISIBLE);
                                }
                                loading.setVisibility(View.GONE);
                            }
                        }

                        @Override
                        public void onFailure(Call<ResultForgotPassword> call, Throwable t) {

                        }
                    });
                }else {
                    alertDialog("Mật khẩu phải lớn hơn 6 ký tự!!!");
                }
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
                        startActivity(new Intent(LayLaiMatKhauActivity.this, LoginActivity.class));
                        finish();
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