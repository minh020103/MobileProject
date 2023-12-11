package com.example.mobileproject.api.admin;

import com.example.mobileproject.api.Const;
import com.example.mobileproject.datamodel.Admin;
import com.example.mobileproject.datamodel.ChinhSach;
import com.example.mobileproject.datamodel.TaiKhoan;
import com.example.mobileproject.datamodel.TienIch;

import java.util.ArrayList;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
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


    @Multipart
    @POST("api/capnhatthongtinadmin")
    Call<Integer> capNhatThongTinAdmin(@Part("id") int id,
                                        @Part("ten")RequestBody ten,
                                       @Part("soDienThoai")RequestBody soDienThoai,
                                       @Part("soTaiKhoanNganHang") RequestBody soTaiKhoanNganHang,
                                       @Part("tenChuTaiKhoan") RequestBody tenChuTaiKhoan,
                                       @Part MultipartBody.Part hinh
                                       );
    @Multipart
    @POST("api/capnhatthongtinadmin2")
    Call<Integer> capNhatThongTinAdmin2(@Part("id") int id,
                                       @Part("ten")RequestBody ten,
                                       @Part("soDienThoai")RequestBody soDienThoai,
                                       @Part("soTaiKhoanNganHang") RequestBody soTaiKhoanNganHang,
                                       @Part("tenChuTaiKhoan") RequestBody tenChuTaiKhoan
    );
    @GET("api/taikhoan")
    Call<TaiKhoan> layTaiKhoanXuong(@Query("id") int id);
    @GET("api/thongtinadmin")
    Call<Admin> layThongTinAdmin(@Query("id") int id);

    @PATCH("api/doimatkhautaikhoan")
    Call<Integer> capNhatMatKhau(@Query("id") int id,@Query("matkhaumoi") String matkhaumoi);

    @GET("api/laytatcatienich")
    Call<ArrayList<TienIch>> layTatCaTienIch();
    @Multipart
    @POST("api/themtienich")
    Call<TienIch> themTienIch(@Part("ten") RequestBody ten, @Part MultipartBody.Part hinh);

    @GET("api/laytienichtheoid")
    Call<TienIch> layTienIchTheoId(@Query("id") int id);
    @Multipart
    @POST("api/capnhattienich")
    Call<Integer> capNhatTienIch(@Part("id") RequestBody id,@Part("ten") RequestBody ten,@Part("trangThai") RequestBody trangThai, @Part MultipartBody.Part hinh);

    @Multipart
    @POST("api/capnhattienich")
    Call<Integer> capNhatTienIch2(@Part("id") RequestBody id,@Part("ten") RequestBody ten,@Part("trangThai") RequestBody trangThai);

    @PATCH("api/capnhattrangthaitienich")
    Call<Integer> capNhatTrangThai(@Query("id") int id);
}
