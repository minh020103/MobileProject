package com.example.mobileproject.api.admin;

import com.example.mobileproject.datamodel.ChuTro;
import com.example.mobileproject.datamodel.GoiDichVu;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiServiceKiet {
    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd")
            .create();

    ApiServiceKiet apiServiceKiet = new Retrofit.Builder()
            .baseUrl("http://192.168.1.102/API_ChuyenDe_12/laravel/public/api/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiServiceKiet.class);

    /* Host */
    @GET("chutro/all")
    Call<List<ChuTro>> getListHostAPI();
    @GET("chutro/chitiet")
    Call<ChuTro> getHostByIdAPI(@Query("idTaiKhoan") int idTaiKhoan);
    @GET("chutro/timKiemTen")
    Call<List<ChuTro>> getHostByNameAPI(@Query("ten") String ten);
    @GET("chutro/timKiemSDT")
    Call<List<ChuTro>> getHostByPhoneAPI(@Query("soDienThoai") String soDienThoai);
    @GET("chutro/lock")
    Call<ChuTro> getHostLockAccountAPI(@Query("id") int id);
    @GET("chutro/unLock")
    Call<ChuTro> getHostUnLockAccountAPI(@Query("id") int id);

    /* Pakage */
    @GET("goi/all")
    Call<List<GoiDichVu>> getListPakageAPI();
    @GET("goi/chitiet")
    Call<GoiDichVu> getPakageByIdAPI(@Query("id") int id);
    @GET("goi/lock")
    Call<GoiDichVu> lockPakageByIdAPI(@Query("id") int id);
    @GET("goi/unLock")
    Call<GoiDichVu> unLockPakageByIdAPI(@Query("id") int id);
    @POST("goi/add")
    Call<GoiDichVu> addPakage(@Query("thoiHan") int thoiHan, @Query("soLuongPhongToiDa") int soLuongPhongToiDa, @Query("gia") int gia);
    @PUT("goi/update")
    Call<GoiDichVu> updatePakage(@Query("id") int id,@Query("thoiHan") int thoiHan, @Query("soLuongPhongToiDa") int soLuongPhongToiDa, @Query("gia") int gia);


}
