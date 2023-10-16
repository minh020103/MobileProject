package com.example.mobileproject.activity.login;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.mobileproject.R;

public class RegisterActivity extends AppCompatActivity {

    EditText edtUserName, edtPassWord, edtCPassWord;
    Button btnRegister;
    String userName, passwWord, email, cPassWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity_layout);

        edtUserName = findViewById(R.id.edt_username);
        edtPassWord = findViewById(R.id.edt_password);
        edtCPassWord = findViewById(R.id.edt_confrm_password);

        btnRegister = findViewById(R.id.btn_register);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkRegister();
            }


        });
    }

    private void checkRegister() {
        userName = edtUserName.getText().toString();
        passwWord = edtPassWord.getText().toString();
        cPassWord = edtCPassWord.getText().toString();

        if (userName.isEmpty() || passwWord.isEmpty() || email.isEmpty()) {
            alertFail("Username, Email and Password is required");
        } else if (!passwWord.equals(cPassWord)) {
            alertFail("Password and Confirm Password doesn't match. ");
        } else {
            sendRegister();
        }
    }

    private void sendRegister() {
        alertSuccess("Register is succesfuly");
    }

    private void alertSuccess(String s) {
        new AlertDialog.Builder(this)
                .setTitle("Success")
                .setIcon(R.drawable.icon_check)
                .setMessage(s)
                .setPositiveButton("Login", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        onBackPressed();
                    }
                }).show();
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
