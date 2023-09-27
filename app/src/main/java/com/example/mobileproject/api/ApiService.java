package com.example.mobileproject.api;


import com.example.mobileproject.models.Account;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    String BASE_URL="http://192.168.1.11/";


    @GET("laravel/public/api/account/changeP/id/{id}/matkhau/{matkhau}")
    Call<Account> changeP(@Query("id")String tk, @Query("matkhau")String mk);
}
