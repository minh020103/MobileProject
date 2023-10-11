package com.example.mobileproject.api.admin;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiServiceNghiem {
    static String BASE_URL = "http://192.168.190.1/3t/laravel/public/";
    ApiServiceNghiem apiService =new Retrofit.Builder()
            .baseUrl(ApiServiceNghiem.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ApiServiceNghiem.class);
}
