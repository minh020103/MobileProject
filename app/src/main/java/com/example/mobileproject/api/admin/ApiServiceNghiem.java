package com.example.mobileproject.api.admin;

import com.example.mobileproject.api.Const;
import com.example.mobileproject.datamodel.TienIch;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Multipart;

public interface ApiServiceNghiem {
    ApiServiceNghiem apiService =new Retrofit.Builder()
            .baseUrl(Const.DOMAIN)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ApiServiceNghiem.class);
    @GET("api/laytatcatienich")
    Call<ArrayList<TienIch>> layTatCaTienIch();

}
