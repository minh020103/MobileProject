package com.example.mobileproject.apiKiet;

import com.example.mobileproject.model.HostModel;
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

    /* Host */
    @GET("hostAll.php")
    Call<List<HostModel>> getListHostAPI();
    @GET("hostByID.php")
    Call<HostModel> getHostByIdAPI(@Query("Id") int Id);
    @GET("hostByName.php")
    Call<List<HostModel>> getHostByNameAPI(@Query("TenNguoiDung") String TenNguoiDung);
    @GET("hostByPhone.php")
    Call<List<HostModel>> getHostByPhoneAPI(@Query("SoDienThoai") String SoDienThoai);

    /* Service */
    //@GET("serviceAll.php")
    //Call<List<HostModel>> getListHostAPI();
}
