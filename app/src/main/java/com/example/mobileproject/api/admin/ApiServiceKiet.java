package com.example.mobileproject.api.admin;

import com.example.mobileproject.api.Const;
import com.example.mobileproject.datamodel.ChuTro;
import com.example.mobileproject.datamodel.Goi;
import com.example.mobileproject.datamodel.TaiKhoan;
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


    ApiServiceKiet apiServiceKiet = new Retrofit.Builder()
                .baseUrl(Const.DOMAIN)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiServiceKiet.class);

    /* Host */
    @GET("api/chutro/daxacthuc")
    Call<List<ChuTro>> getListHostAPI();
    @GET("api/chutro/chitiet")
    Call<ChuTro> getHostByIdAPI(@Query("idTaiKhoan") int idTaiKhoan);
    @GET("api/chutro/timKiemTenChuTroXacThuc")
    Call<List<ChuTro>> getHostByNameAPI(@Query("ten") String ten);
    @GET("api/chutro/timKiemSDTChuTroXacThuc")
    Call<List<ChuTro>> getHostByPhoneAPI(@Query("soDienThoai") String soDienThoai);

    /* Account */
    @PATCH("api/capnhattrangthai")
    Call<Integer> thayDoiTrangThaiTaiKhoan(@Query("id") int id);

    /* Pakage */
    @GET("api/goi/all")
    Call<List<Goi>> getListPakageAPI();
    @GET("api/goi/chitiet")
    Call<Goi> getPakageByIdAPI(@Query("id") int id);
    @GET("api/goi/lock")
    Call<Goi> lockPakageByIdAPI(@Query("id") int id);
    @GET("api/goi/unLock")
    Call<Goi> unLockPakageByIdAPI(@Query("id") int id);
    @POST("api/goi/add")
    Call<Goi> addPakage(@Query("thoiHan") int thoiHan, @Query("soLuongPhongToiDa") int soLuongPhongToiDa, @Query("gia") int gia);
    @PUT("api/goi/update")
    Call<Goi> updatePakage(@Query("id") int id,@Query("thoiHan") int thoiHan, @Query("soLuongPhongToiDa") int soLuongPhongToiDa, @Query("gia") int gia);


}
