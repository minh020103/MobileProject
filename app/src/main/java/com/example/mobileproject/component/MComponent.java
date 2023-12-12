package com.example.mobileproject.component;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.mobileproject.api.admin.ApiServiceMinh;
import com.example.mobileproject.datamodel.FirebaseCloudMessaging;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MComponent {
    //kyTuThayThe là phần bị mất đi được thay thế bằng kyTuThayThe VD kyTuThayThe="..." thì str ở trên sẽ thành "012345..."
    public static String boChuoiPhiaSau(String str, int soLuongKyTu, String kyTuThayThe){
        String res = str.length() >= soLuongKyTu+1?str.substring(0, soLuongKyTu)+kyTuThayThe:str;
        return res;
    }
    //Lưu token ứng dụng của thiết bị (idAccount)
    //idAccount là tài khoản đã đăng nhập vào ứng dụng
    public static void saveTokenAppDevice(int idAccount){
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.w("TAG", "Fetching FCM registration token failed", task.getException());
                            return;
                        }

                        // Get new FCM registration token
                        String token = task.getResult();

                        ApiServiceMinh.apiService.saveTokenDeviceOfAccount(token, idAccount).enqueue(new Callback<FirebaseCloudMessaging>() {
                            @Override
                            public void onResponse(Call<FirebaseCloudMessaging> call, Response<FirebaseCloudMessaging> response) {
                                if (response.code() == 200) {
                                    Log.d("TAG", "onResponse: SAVE TOKEN COMPLATED");
                                }
                            }

                            @Override
                            public void onFailure(Call<FirebaseCloudMessaging> call, Throwable t) {

                            }
                        });
                    }
                });
    }
    //Xóa token
    public static void deleteTokenDivice(){
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.w("TAG", "Fetching FCM registration token failed", task.getException());
                            return;
                        }

                        // Get new FCM registration token
                        String token = task.getResult();

                        ApiServiceMinh.apiService.deleteTokenDeviceOfAccount(token).enqueue(new Callback<Integer>() {
                            @Override
                            public void onResponse(Call<Integer> call, Response<Integer> response) {
                                if (response.code() == 200) {
                                    Log.d("TAG", "onResponse: DELETE TOKEN COMPLATED");
                                }
                            }

                            @Override
                            public void onFailure(Call<Integer> call, Throwable t) {

                            }
                        });
                    }
                });

    }
}
