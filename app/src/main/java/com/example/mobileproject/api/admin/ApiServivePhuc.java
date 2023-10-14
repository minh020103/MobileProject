package com.example.mobileproject.api.admin;

import com.example.mobileproject.api.Const;
import com.example.mobileproject.datamodel.Banner;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiServivePhuc {

//    http://192.168.147.245/TimTroTot/laravel/public/api/banner/all

    ApiServivePhuc apiService = new Retrofit.Builder()
            .baseUrl(Const.DOMAIN)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiServivePhuc.class);

    @GET("api/banner/all")
    Call<List<Banner>> getListBanner();
}
