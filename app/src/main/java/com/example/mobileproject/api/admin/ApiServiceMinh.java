package com.example.mobileproject.api.admin;

import com.example.mobileproject.api.Const;
import com.example.mobileproject.datamodel.ChuTro;
import com.example.mobileproject.datamodel.FirebaseCloudMessaging;
import com.example.mobileproject.datamodel.ResultForgotPassword;
import com.example.mobileproject.datamodel.TaiKhoan;
import com.example.mobileproject.datamodel.ThongBao;
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
import retrofit2.http.POST;
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
    @GET("api/notification/number")
    Call<Integer> demTongSoThongBao();

    // Khi đăng nhập thành công thì lưu token device lên trên database rồi mới đăng nhập vào ứng dụng
    @POST("api/fcm/savetoken")
    Call<FirebaseCloudMessaging> saveTokenDeviceOfAccount(@Query("token") String token, @Query("idTaiKhoan") int idTaiKhoan);
    // Khi đăng xuất thì phải dùng hàm này khi xóa thành công trên database rồi mới được xóa idTaiKhoan trong thiết bị và đăng xuất
    @DELETE("api/fcm/delete")
    Call<Integer> deleteTokenDeviceOfAccount(@Query("token") String token);

    @GET("api/taikhoan/all/type")
    Call<List<TaiKhoan>> layTatCaTaiKhoanTheoLoaiTaiKhoan(@Query("loaiTaiKhoan") int loaiTaiKhoan);

    @GET("api/fcm/gettoken")
    Call<List<FirebaseCloudMessaging>> layTatCaTokenCuaTaiKhoan(@Query("idTaiKhoan") int idTaiKhoan);

    @POST("api/notification/create")
        Call<ThongBao> sendNotificationResult(@Query("idTaiKhoanGui") int idTaiKhoanGui, @Query("idTaiKhoanNhan") int idTaiKhoanNhan, @Query("title") String title, @Query("noiDung") String noiDung, @Query("trangThai") int trangThai, @Query("trangThaiNhan") int trangThaiNhan);

    @GET("api/checkuser")
    Call<TaiKhoan> layTaiKhoanTheoUsername(@Query("tenTaiKhoan") String tenTaiKhoan);
    @POST("api/forgotpassword")
    Call<ResultForgotPassword> sendEmailForgotPassword(@Query("idTaiKhoan") int idTaiKhoan, @Query("email") String email);
    @POST("api/checkcode")
    Call<ResultForgotPassword> resultForgotpassword(@Query("idTaiKhoan") int idTaiKhoan, @Query("code") int code, @Query("matKhau") String matKhau);

}
