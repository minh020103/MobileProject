package com.example.mobileproject;





import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mobileproject.api.ApiService;
import com.example.mobileproject.models.Account;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ManHinhDoiMKAdmin extends AppCompatActivity {

    private Account account = new Account();
    EditText matkhauhientai;
    EditText matkhaumoi;
    EditText matkhaumoixacnhan;
    Button btnXacNhan;

    String idAccount ;

    String mkTraVe ="";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manhinhdoimatkhau);
        anhXa();
        idAccount=1+"";
//       batSuKien();

        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layDuLieuHienTai();

//                xuLiSuKien();

            }
        });



    }


//    private void xuLiSuKien(){
//        if(kiemTra()&&xuLyBienTraVe()){
//
//
//                AlertDialog.Builder builder = new AlertDialog.Builder(ManHinhDoiMKAdmin.this)
//                        .setMessage("Bạn có chắc muốn đối mật khẩu!")
//                        .setPositiveButton("OK", new
//                                DialogInterface.OnClickListener() {
//                                    @Override
//                                    public void onClick(DialogInterface dialogInterface, int i) {
//
//                                    }
//                                });
//                builder.create().show();
//
//        }
//        else{
//            AlertDialog.Builder builder = new AlertDialog.Builder(ManHinhDoiMKAdmin.this)
//                    .setMessage("Không Đúng!")
//                    .setPositiveButton("OK", new
//                            DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialogInterface, int i) {
//
//                                }
//                            });
//            builder.create().show();
//        }
//
//
//
//    }


    private void layDuLieuHienTai(){

        if(kiemTra()){

            Retrofit retrofit =new Retrofit.Builder()
                    .baseUrl(ApiService.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            ApiService apiService = retrofit.create(ApiService.class);
            Call<Account> accountCall = apiService.getAccountById(idAccount);
            accountCall.enqueue(new Callback<Account>() {
                @Override
                public void onResponse(Call<Account> call, Response<Account> response) {
                    Account a = response.body();
                    if(matkhauhientai.getText().toString().equals(a.getMatkhau())){
                        xuLyBienTraVe();
                    }
                    else{
                        AlertDialog.Builder builder = new AlertDialog.Builder(ManHinhDoiMKAdmin.this);

                        builder
                                .setMessage("Mật Khẩu Hiện Tại Không Đúng!")
                                .setPositiveButton("OK", new
                                        DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {

                                            }
                                        });
                        builder.create().show();
                    }
                }

                @Override
                public void onFailure(Call<Account> call, Throwable t) {

                }
            });
        }
        else{
            AlertDialog.Builder builder = new AlertDialog.Builder(ManHinhDoiMKAdmin.this);

            builder
                    .setMessage("Dữ liệu không đúng!")
                    .setPositiveButton("OK", new
                            DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            });
            builder.create().show();
        }



        }
    private void xuLyBienTraVe(){
        AlertDialog.Builder builder = new AlertDialog.Builder(ManHinhDoiMKAdmin.this);

          builder
                        .setMessage("Bạn có chắc muốn đối mật khẩu!")
                        .setPositiveButton("OK", new
                                DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        Retrofit retrofit =new Retrofit.Builder()
                                                .baseUrl(ApiService.BASE_URL)
                                                .addConverterFactory(GsonConverterFactory.create())
                                                .build();
                                        ApiService apiService = retrofit.create(ApiService.class);
                                        Call<Integer> call = apiService.changePassword(idAccount,matkhaumoi.getText().toString());
                                        call.enqueue(new Callback<Integer>() {
                                            @Override
                                            public void onResponse(Call<Integer> call, Response<Integer> response) {
                                                AlertDialog.Builder builder1 = new AlertDialog.Builder(ManHinhDoiMKAdmin.this);

                                                builder1
                                                        .setMessage("Đổi mật khẩu thành công!")
                                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                            @Override
                                                            public void onClick(DialogInterface dialogInterface, int i) {

                                                            }
                                                        });
                                                builder1.create().show();

                                            }

                                            @Override
                                            public void onFailure(Call<Integer> call, Throwable t) {

                                            }
                                        });


                                    }
                                }).setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
                builder.create().show();



    }



    private void xuLi() {

        if(kiemTra()){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(ApiService.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            ApiService apiService = retrofit.create(ApiService.class);
            Call<Account> call = apiService.getAccountById(idAccount);

            call.enqueue(new Callback<Account>() {
                @Override
                public void onResponse(Call<Account> call, Response<Account> response) {
                    mkTraVe = response.body().getMatkhau();
                    xuLiTraVe();
                }


                @Override
                public void onFailure(Call<Account> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Thất Bại", Toast.LENGTH_SHORT).show();
                }
            });

        }else{
            AlertDialog.Builder builder = new AlertDialog.Builder(ManHinhDoiMKAdmin.this)
                    .setMessage("Thất Bại Vòng Ngoài!")
                    .setPositiveButton("OK", new
                            DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            });
            builder.create().show();
        }



    }

    private boolean kiemTra(){
        if(!matkhaumoi.getText().toString().isEmpty()&&!matkhaumoixacnhan.getText().toString().isEmpty()&&!matkhauhientai.getText().toString().isEmpty()&&matkhaumoi.getText().toString().equals(matkhaumoixacnhan.getText().toString())){
            return true;
        }
        return false;
    }

    private void anhXa(){
        matkhauhientai = findViewById(R.id.matkhauhientai);
        matkhaumoi = findViewById(R.id.matkhaumoi);
        matkhaumoixacnhan = findViewById(R.id.matkhaumoixacnhan);
        btnXacNhan = findViewById(R.id.btnXacNhan);
    }
    private void xuLiTraVe(){
        if(mkTraVe.equals(matkhauhientai.getText().toString())){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(ApiService.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            ApiService apiService = retrofit.create(ApiService.class);
            Call<Integer> integerCall = apiService.changePassword(idAccount,matkhaumoi.getText().toString());
            integerCall.enqueue(new Callback<Integer>() {
                @Override
                public void onResponse(Call<Integer> call, Response<Integer> response) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(ManHinhDoiMKAdmin.this)
                            .setMessage("Ngon Lành!")
                            .setPositiveButton("OK", new
                                    DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {

                                        }
                                    });
                    builder.create().show();
                }

                @Override
                public void onFailure(Call<Integer> call, Throwable t) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(ManHinhDoiMKAdmin.this)
                            .setMessage("Thất Bại Vòng Ngoài!")
                            .setPositiveButton("OK", new
                                    DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {

                                        }
                                    });
                    builder.create().show();
                }
            });
        }
    }
}
