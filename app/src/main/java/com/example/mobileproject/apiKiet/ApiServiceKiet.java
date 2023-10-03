package com.example.mobileproject.apiKiet;

import com.example.mobileproject.model.ManagerModel;
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
            .baseUrl("http://192.168.2.6/API_ChuyenDe2/api/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiServiceKiet.class);
    @GET("index")
    Call<List<ManagerModel>> getListManagerAPI();

    @GET("managerByName.php")
    Call<List<ManagerModel>> getManagerByIdAPI(@Query("tenNguoiQuanLy") String tenNguoiQuanLy);

    @GET("managerByArea.php")
    Call<List<ManagerModel>> getManagerByAreaAPI(@Query("tinhQuanLi") String tinhQuanLi);
}
