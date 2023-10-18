package com.example.mobileproject.activity.login;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobileproject.R;
import com.example.mobileproject.activity.admin.AdminActivity;
import com.example.mobileproject.api.admin.ApiServivePhuc;
import com.example.mobileproject.datamodel.TaiKhoan;

import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    EditText edtUsername, edtPassWord;
    Button btnLogin;
    TextView tvRegister,tvErrorUsnPass, tvErrorEmpty,tvErrorMissing;


    String userName, passWord;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity_layout);

        edtPassWord = findViewById(R.id.edt_password);
        edtUsername = findViewById(R.id.edt_username);
        btnLogin = findViewById(R.id.btn_login);
        tvRegister = findViewById(R.id.tv_register);
        tvErrorUsnPass = findViewById(R.id.tv_error_usn_pass);
        tvErrorEmpty = findViewById(R.id.tv_error_empty);
        tvErrorMissing = findViewById(R.id.tv_error_missing_charter);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkLogin();
            }
        });

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    private void checkLogin() {
        userName = edtUsername.getText().toString();
        passWord = edtPassWord.getText().toString();
        if (userName.isEmpty() || passWord.isEmpty()) {
            tvErrorEmpty.setVisibility(View.VISIBLE);
        } else if(userName.length() < 6 || passWord.length() < 6) {
            tvErrorEmpty.setVisibility(View.GONE);
            tvErrorMissing.setVisibility(View.VISIBLE);
        } else {
            clickButtonLogin();
        }
    }

    private void clickButtonLogin() {
        Call<TaiKhoan> login = ApiServivePhuc.apiService.login(userName, passWord);
        login.enqueue(new Callback<TaiKhoan>() {
            @Override
            public void onResponse(Call<TaiKhoan> call, Response<TaiKhoan> response) {
                startActivity(new Intent(LoginActivity.this, AdminActivity.class));
            }
            @Override
            public void onFailure(Call<TaiKhoan> call, Throwable t) {
                tvErrorEmpty.setVisibility(View.GONE);
                tvErrorMissing.setVisibility(View.GONE);
                tvErrorUsnPass.setVisibility(View.VISIBLE);
            }
        });


    }

    private void alertFail(String s) {
        new AlertDialog.Builder(this)
                .setTitle("Failed")
                .setIcon(R.drawable.icon_warning)
                .setMessage(s)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).show();
    }


}