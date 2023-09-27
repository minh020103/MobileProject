package com.example.mobileproject;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mobileproject.api.ApiService;
import com.example.mobileproject.models.Account;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ManHinhDoiMKAdmin extends AppCompatActivity {

    EditText matkhauhientai;
    EditText matkhaumoi;
    EditText matkhaumoixacnhan;
    Button btnXacNhan;
    ApiService apiService;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manhinhdoimatkhau);
        anhXa();
        batSuKien();
    }

    private void batSuKien(){
        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(kiemTra()){
                    Retrofit retrofit =new Retrofit.Builder()
                            .baseUrl(ApiService.BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                    apiService = retrofit.create(ApiService.class);
                    Call<Account> call = apiService.changeP(1+"",5+"");
                    call.enqueue(new Callback<Account>() {
                        @Override
                        public void onResponse(Call<Account> call, Response<Account> response) {
                            Toast.makeText(getApplicationContext(), "ngon", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<Account> call, Throwable t) {

                        }
                    });


                }
            }
        });
    }


    private boolean kiemTra(){
        boolean kt=false;
        if(!matkhauhientai.getText().toString().isEmpty()&&!matkhaumoi.getText().toString().isEmpty()&&!matkhaumoixacnhan.getText().toString().isEmpty()&&matkhaumoi.getText().toString().equals(matkhaumoixacnhan.getText().toString()));
        {
            kt=true;
        }
        return kt;
    }
    private void anhXa(){
        matkhauhientai = findViewById(R.id.matkhauhientai);
        matkhaumoi = findViewById(R.id.matkhaumoi);
        matkhaumoixacnhan = findViewById(R.id.matkhaumoixacnhan);
        btnXacNhan = findViewById(R.id.btnXacNhan);
    }
}
