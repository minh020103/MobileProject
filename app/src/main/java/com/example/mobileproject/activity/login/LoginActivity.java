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

public class LoginActivity extends AppCompatActivity {

    EditText edtUsername, edtPassWord;
    Button btnLogin;
    TextView tvRegister;


    String userName,passWord;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity_layout);

        edtPassWord = findViewById(R.id.edt_password);
        edtUsername = findViewById(R.id.edt_username);
        btnLogin = findViewById(R.id.btn_login);
        tvRegister = findViewById(R.id.tv_register);

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

    private void checkLogin(){
        userName = edtUsername.getText().toString();
        passWord = edtPassWord.getText().toString();
        if (userName.isEmpty() || passWord.isEmpty()){
            alertFail("Username and Password is required");
        }else {
            clickButtonLogin();
        }
    }

    private void clickButtonLogin() {

        Intent intent = new Intent(LoginActivity.this, AdminActivity.class);
        startActivity(intent);

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