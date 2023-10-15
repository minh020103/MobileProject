package com.example.mobileproject.api.admin;

import com.example.mobileproject.datamodel.ChuTro;
import com.example.mobileproject.datamodel.Goi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface ApiServiceKiet {


    ApiServiceKiet apiServiceKiet = new Retrofit.Builder()
            .baseUrl("http://192.168.1.64/API_ChuyenDe_12/laravel/public/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiServiceKiet.class);

    /* Host */
    @GET("chutro/daxacthuc")
    Call<List<ChuTro>> getListHostAPI();
    @GET("chutro/chitiet")
    Call<ChuTro> getHostByIdAPI(@Query("idTaiKhoan") int idTaiKhoan);
    @GET("chutro/timKiemTen")
    Call<List<ChuTro>> getHostByNameAPI(@Query("ten") String ten);
    @GET("chutro/timKiemSDT")
    Call<List<ChuTro>> getHostByPhoneAPI(@Query("soDienThoai") String soDienThoai);

    /* Account */
    @PATCH("capnhattrangthai")
    Call<Integer> thayDoiTrangThaiTaiKhoan(@Query("id") int id);

    /* Pakage */
    @GET("goi/all")
    Call<List<Goi>> getListPakageAPI();
    @GET("goi/chitiet")
    Call<Goi> getPakageByIdAPI(@Query("id") int id);
    @GET("goi/lock")
    Call<Goi> lockPakageByIdAPI(@Query("id") int id);
    @GET("goi/unLock")
    Call<Goi> unLockPakageByIdAPI(@Query("id") int id);
    @POST("goi/add")
    Call<Goi> addPakage(@Query("thoiHan") int thoiHan, @Query("soLuongPhongToiDa") int soLuongPhongToiDa, @Query("gia") int gia);
    @PUT("goi/update")
    Call<Goi> updatePakage(@Query("id") int id, @Query("thoiHan") int thoiHan, @Query("soLuongPhongToiDa") int soLuongPhongToiDa, @Query("gia") int gia);


}
