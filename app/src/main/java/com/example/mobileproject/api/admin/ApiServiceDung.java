package com.example.mobileproject.api.admin;

import com.example.mobileproject.api.Const;
import com.example.mobileproject.datamodel.Phuong;
import com.example.mobileproject.datamodel.Quan;
import com.example.mobileproject.datamodel.TienIch;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

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

public interface ApiServiceDung {
    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd")
            .create();

    ApiServiceDung apiServiceDung = new Retrofit.Builder()
            .baseUrl(Const.DOMAIN)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiServiceDung.class);

    /* Host */
    @GET("api/laytatcaquan")
    Call<ArrayList<Quan>> layTatCaQuan();

    @GET("api/phuong/layphuongtheoquan")
    Call<ArrayList<Phuong>> layTatCaPhuongTheoQuan(@Query("idQuan") int idQuan);

    @GET("api/layquantheoid")
    Call<Quan> layQuanTheoID(@Query("id") int id);

    @Multipart
    @POST("api/themquan")
    Call<Quan> themQuan(@Part("tenQuan") RequestBody ten, @Part MultipartBody.Part hinh);

    @Multipart
    @POST("api/capnhatquan")
    Call<Integer> capnhatquan(@Part("id") RequestBody id,@Part("tenQuan") RequestBody tenQuan,@Part("trangThai") RequestBody trangThai, @Part MultipartBody.Part hinh);

    @Multipart
    @POST("api/capnhatquan")
    Call<Integer> capnhatquan2(@Part("id") RequestBody id,@Part("tenQuan") RequestBody tenQuan,@Part("trangThai") RequestBody trangThai);

    @PATCH("api/capnhattrangthaiquan")
    Call<Integer> capnhattrangthaiquan(@Query("id") int id);

    //Phuong
    @Multipart
    @POST("api/themphuong")
    Call<Phuong> themphuong(@Part("tenPhuong") RequestBody ten,@Part("idQuan") int idQuan);

    @Multipart
    @POST("api/capnhatphuong")
    Call<Integer> editPhuong(@Part("id") int id,@Part("tenPhuong") RequestBody ten,@Part("idQuan") int idQuan,@Part("trangThai") Integer trangThai);

    @PATCH("api/capnhattrangthaiphuong")
    Call<Integer> capnhattrangthaiphuong(@Query("id") int id);
}
