package com.example.mobileproject.api.admin;

import com.example.mobileproject.datamodel.ChuTro;
import com.example.mobileproject.datamodel.GoiDichVu;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
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
    @GET("hostByID.php")
    Call<ChuTro> getHostByIdAPI(@Query("Id") int Id);
    @GET("hostByName.php")
    Call<List<ChuTro>> getHostByNameAPI(@Query("TenNguoiDung") String TenNguoiDung);
    @GET("hostByPhone.php")
    Call<List<ChuTro>> getHostByPhoneAPI(@Query("SoDienThoai") String SoDienThoai);
    @GET("hostLockAccount.php")
    Call<ChuTro> getHostLockAccountAPI(@Query("Id") int Id);
    @GET("hostUnLockAccount.php")
    Call<ChuTro> getHostUnLockAccountAPI(@Query("Id") int Id);

    /* Pakage */
    @GET("goi/all")
    Call<List<GoiDichVu>> getListPakageAPI();
    @GET("serviceByID.php")
    Call<GoiDichVu> getPakageByIdAPI(@Query("Id") int Id);

}
