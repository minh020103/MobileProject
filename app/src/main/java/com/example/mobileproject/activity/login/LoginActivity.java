package com.example.mobileproject.activity.login;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobileproject.R;
import com.example.mobileproject.activity.admin.AdminActivity;
import com.example.mobileproject.api.Const;
import com.example.mobileproject.api.admin.ApiServiceMinh;
import com.example.mobileproject.api.admin.ApiServivePhuc;
import com.example.mobileproject.component.MComponent;
import com.example.mobileproject.datamodel.FirebaseCloudMessaging;
import com.example.mobileproject.datamodel.TaiKhoan;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    EditText edtUsername, edtPassWord;
    Button btnLogin;
    TextView tvRegister,tvErrorUsnPass, tvErrorEmpty,tvErrorMissing;

    ProgressBar progLoading;

    String userName, passWord;
    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity_layout);
        sharedPreferences = sharedPreferences = this.getSharedPreferences(Const.PRE_LOGIN, Context.MODE_PRIVATE);
        edtPassWord = findViewById(R.id.edt_password);
        edtUsername = findViewById(R.id.edt_username);
        btnLogin = findViewById(R.id.btn_login);
        tvRegister = findViewById(R.id.tv_register);
        tvErrorUsnPass = findViewById(R.id.tv_error_usn_pass);
        tvErrorEmpty = findViewById(R.id.tv_error_empty);
        tvErrorMissing = findViewById(R.id.tv_error_missing_charter);
        progLoading = findViewById(R.id.loading);

        askNotificationPermission();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progLoading.setVisibility(View.VISIBLE);
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
            progLoading.setVisibility(View.GONE);
            tvErrorMissing.setVisibility(View.GONE);
            tvErrorUsnPass.setVisibility(View.GONE);
            tvErrorEmpty.setVisibility(View.VISIBLE);
        } else if(userName.length() < 6 || passWord.length() < 6) {
            progLoading.setVisibility(View.GONE);
            tvErrorUsnPass.setVisibility(View.GONE);
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
                if (response.code() == 200){
                    if (response.body() != null){
                        MComponent.saveTokenAppDevice(response.body().getId());
                        sharedPreferences.edit().putInt("idTaiKhoan", response.body().getId()).commit();
                        progLoading.setVisibility(View.GONE);
                        startActivity(new Intent(LoginActivity.this, AdminActivity.class));
                        finish();
                    }
                }

            }
            @Override
            public void onFailure(Call<TaiKhoan> call, Throwable t) {
                progLoading.setVisibility(View.GONE);
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
//    private void requestPermisstion(){
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
//            if (ContextCompat.checkSelfPermission(
//                    LoginActivity.this, Manifest.permission.POST_NOTIFICATIONS) ==
//                    PackageManager.PERMISSION_GRANTED){
//            }
//            else {
//                requestPermissions(new String[]{Manifest.permission.POST_NOTIFICATIONS}, 111);
//            }
//        }
//    }

    // Declare the launcher at the top of your Activity/Fragment:
    private final ActivityResultLauncher<String> requestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                if (isGranted) {
                    // FCM SDK (and your app) can post notifications.
                } else {
                    // TODO: Inform user that that your app will not show notifications.
                }
            });

    private void askNotificationPermission() {
        // This is only necessary for API level >= 33 (TIRAMISU)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) ==
                    PackageManager.PERMISSION_GRANTED) {
                // FCM SDK (and your app) can post notifications.
            } else if (shouldShowRequestPermissionRationale(Manifest.permission.POST_NOTIFICATIONS)) {
                // TODO: display an educational UI explaining to the user the features that will be enabled
                //       by them granting the POST_NOTIFICATION permission. This UI should provide the user
                //       "OK" and "No thanks" buttons. If the user selects "OK," directly request the permission.
                //       If the user selects "No thanks," allow the user to continue without notifications.
            } else {
                // Directly ask for the permission
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS);
            }
        }
    }


}