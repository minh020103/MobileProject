package com.example.mobileproject.api.admin;

import com.example.mobileproject.api.Const;
import com.example.mobileproject.datamodel.ChuTro;
import com.example.mobileproject.datamodel.YeuCauDangKyGoi;
import com.example.mobileproject.datamodel.YeuCauXacThuc;
import com.example.mobileproject.datamodel.YeuCauXoaPhong;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.PUT;
import retrofit2.http.Query;


public interface ApiServiceMinh {
    ApiServiceMinh apiService = new Retrofit.Builder()
            .baseUrl(Const.DOMAIN)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiServiceMinh.class);

    @PATCH("api/chutro/chapnhanxacthuc")
    Call<Integer> xacThucThongTinChuTro(@Query("id") int idChuTro);

    @GET("api/xacthucchutro/all")
    Call<List<YeuCauXacThuc>> layTatCaYeuCauXacThuc();
    @PATCH("api/xacthucchutro/xacthuc")
    Call<Integer> xacThucChuTro(@Query("idChuTro") int idChuTro);
    @GET("api/xacthucchutro/chitiet")
    Call<YeuCauXacThuc> layThongTinChiTietYeuCauXacThuc(@Query("idChuTro")int idChuTro);
    @DELETE("api/xacthucchutro/xoa")
    Call<Integer> xoaYeuCauXacThucCuaChuTro(@Query("idChuTro") int idChuTro);

    @GET("api/yeucaudangky/all")
    Call<List<YeuCauDangKyGoi>> layTatCaYeuCauDangKyGoi();
    @GET("api/yeucaudangky/chitiet")
    Call<YeuCauDangKyGoi> layChiTietYeuCauDangKyGoi(@Query("id") int id);
    @PATCH("api/yeucaudangky/xacthuc")
    Call<Integer> xacNhanYeuCauDangKy(@Query("id")int id);
    @DELETE("api/yeucaudangky/huy")
    Call<Integer> huyYeuCauDangKyGoi(@Query("id")int id);

    @GET("api/yeucauxoaphong/all")
    Call<List<YeuCauXoaPhong>> layDanhSachYeuCauXoaPhong();
    @GET("api/yeucauxoaphong/chitiet")
    Call<YeuCauXoaPhong> chiTietYeuCauXoaPhong(@Query("id") int id);
    @DELETE("api/yeucauxoaphong/huy")
    Call<Integer> huyYeuCauXoaPhong(@Query("id") int id);

    @DELETE("api/phongtro/delete")
    Call<Integer> xacNhanYeuCauXoaPhong(@Query("id") int idPhong);
}
