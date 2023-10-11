package com.example.mobileproject.api.admin;

import com.example.mobileproject.api.Const;
import com.example.mobileproject.datamodel.ChinhSach;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiServiceNghiem {

    ApiServiceNghiem apiService =new Retrofit.Builder()
            .baseUrl(Const.DOMAIN)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ApiServiceNghiem.class);

    @GET("api/chinhsach")
    Call<ChinhSach> layChinhSachXuong(@Query("id") int id);

    @PUT("api/capnhatchinhsach")
    Call<Integer> capNhatChinhSach(@Query("id") int id,@Body ChinhSach chinhSach);




}
