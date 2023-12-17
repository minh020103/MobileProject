package com.example.mobileproject.fragment.admin;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import com.bumptech.glide.Glide;
import com.example.mobileproject.R;
import com.example.mobileproject.activity.admin.DetailPolicyActivity;
import com.example.mobileproject.activity.admin.EditPasswordAdminActivity;
import com.example.mobileproject.activity.admin.EditPolicyActivity;
import com.example.mobileproject.activity.admin.EditProfileAdminActivity;
import com.example.mobileproject.activity.login.LoginActivity;
import com.example.mobileproject.api.Const;
import com.example.mobileproject.api.admin.ApiServiceNghiem;
import com.example.mobileproject.component.MComponent;
import com.example.mobileproject.datamodel.Admin;
import com.example.mobileproject.datamodel.TaiKhoan;
import com.makeramen.roundedimageview.RoundedImageView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileFragment extends AbstractFragment{

    LinearLayout nextChinhSach;
    LinearLayout nextDoiThongTin;
    LinearLayout nextDoiMatKhau;
    LinearLayout dangXuat;

    RoundedImageView imgAdmin;
    TextView tenAdmin;
    TextView sdtAdmin;
    TextView stkAdmin;
    TextView tenNganHangAdmin;
    private SharedPreferences sharedPreferences;
    int idTaiKhoan;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentLayout = null;
        sharedPreferences = getActivity().getSharedPreferences(Const.PRE_LOGIN, Context.MODE_PRIVATE);
        idTaiKhoan = sharedPreferences.getInt("idTaiKhoan", -idTaiKhoan);
        fragmentLayout = inflater.inflate(R.layout.fragment_admin_profile_layout, container, false);
        anhXa(fragmentLayout);
        layDuLieuXuong();
        nextChinhSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), DetailPolicyActivity.class);
                startActivity(intent);
            }
        });
        nextDoiThongTin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), EditProfileAdminActivity.class);
                startActivity(intent);
            }
        });
        nextDoiMatKhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), EditPasswordAdminActivity.class);
                startActivity(intent);
            }
        });
        dangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPreferences = getContext().getSharedPreferences("SharedPreferencesLogin", Context.MODE_PRIVATE);
                MComponent.deleteTokenDivice();
                sharedPreferences.edit().remove("idTaiKhoan").commit();
                startActivity(new Intent(getActivity(), LoginActivity.class));
            }
        });


        return fragmentLayout;
    }

    private void layDuLieuXuong(){
        Call<Admin> call = ApiServiceNghiem.apiService.layThongTinAdmin(idTaiKhoan);
        call.enqueue(new Callback<Admin>() {
            @Override
            public void onResponse(Call<Admin> call, Response<Admin> response) {
                if (response.body() != null){
                    Glide.with(ProfileFragment.this).load(Const.DOMAIN+response.body().getHinh()).into(imgAdmin);
                    tenAdmin.setText(response.body().getTen());
                    sdtAdmin.setText(response.body().getSoDienThoai());
                    stkAdmin.setText(response.body().getSoTaiKhoanNganHang());
                    tenNganHangAdmin.setText(response.body().getTenChuTaiKhoan());
                }
            }

            @Override
            public void onFailure(Call<Admin> call, Throwable t) {

            }
        });
    }
    private void anhXa(View fragment){
        nextChinhSach = fragment.findViewById(R.id.nextDoiChinhSach);
        nextDoiThongTin = fragment.findViewById(R.id.nextSuaThongTin);
        nextDoiMatKhau = fragment.findViewById(R.id.nextDoiMK);
        dangXuat = fragment.findViewById(R.id.dangXuat);
        imgAdmin = fragment.findViewById(R.id.imgAdmin);
        tenAdmin = fragment.findViewById(R.id.tenAdmin);
        sdtAdmin = fragment.findViewById(R.id.sdtAdmin);
        stkAdmin = fragment.findViewById(R.id.stkAdmin);
        tenNganHangAdmin = fragment.findViewById(R.id.tenNganHangAdmin);
    }
}
