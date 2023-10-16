package com.example.mobileproject.api.admin;

import com.example.mobileproject.api.Const;
import com.example.mobileproject.datamodel.TienIch;

import java.util.ArrayList;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface ApiServiceNghiem {
    ApiServiceNghiem apiService =new Retrofit.Builder()
            .baseUrl(Const.DOMAIN)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ApiServiceNghiem.class);
    @GET("api/laytatcatienich")
    Call<ArrayList<TienIch>> layTatCaTienIch();
    @Multipart
    @POST("api/themtienich")
    Call<Integer> themTienIch(@Part("ten") RequestBody ten, @Part MultipartBody.Part hinh);

    @GET("api/laytienichtheoid")
    Call<TienIch> layTienIchTheoId(@Query("id") int id);
    @Multipart
    @POST("api/capnhattienich")
    Call<Integer> capNhatTienIch(@Part("id") RequestBody id,@Part("ten") RequestBody ten,@Part("trangThai") RequestBody trangThai, @Part MultipartBody.Part hinh);

    @PATCH("api/capnhattienich2")
    Call<Integer> capNhatTienIch2(@Query("id") int id, @Query("ten") String ten,@Query("trangThai") int trangThai);

    @PATCH("api/capnhattrangthaitienich")
    Call<Integer> capNhatTrangThai(@Query("id") int id);
}
