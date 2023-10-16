package com.example.mobileproject.api.admin;

import com.example.mobileproject.api.Const;
import com.example.mobileproject.datamodel.Banner;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface ApiServivePhuc {

//    http://192.168.147.245/TimTroTot/laravel/public/api/banner/all

    ApiServivePhuc apiService = new Retrofit.Builder()
            .baseUrl(Const.DOMAIN)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiServivePhuc.class);

    @GET("api/banner/all")
    Call<List<Banner>> getListBanner();

    @Multipart
    @POST("api/banner/create")
    Call<Integer> uploadFileBanner(@Part MultipartBody.Part hinhBanner);
    @Multipart
    @POST("api/banner/edit")
    Call<Integer> editBanner(@Query("id") int id, @Part MultipartBody.Part hinhBanner);

    @DELETE("api/banner/delete")
    Call<Integer> deleteBanner(@Query("id")int id);

}
