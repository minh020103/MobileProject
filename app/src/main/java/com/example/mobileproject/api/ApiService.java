package com.example.mobileproject.api;


import com.example.mobileproject.models.Account;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

import retrofit2.http.PATCH;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    String BASE_URL="http://192.168.190.1/";

    @GET("laravel/public/api/account/all")
    Call<List<Account>> getAll();

    @GET("laravel/public/api/account")
    Call<Account> getAccountById(@Query("id")String id);
    @PATCH("laravel/public/api/account/changepassword")
    Call<Integer> changePassword(@Query("id") String id,@Query("matkhau") String matkhau);


}
