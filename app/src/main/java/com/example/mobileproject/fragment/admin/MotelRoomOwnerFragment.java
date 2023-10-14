package com.example.mobileproject.fragment.admin;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileproject.R;
import com.example.mobileproject.activity.admin.MotelRoomOwnerDetailActivity;
import com.example.mobileproject.adapter.admin.ChuTroAdapter;
import com.example.mobileproject.api.admin.ApiServiceKiet;
import com.example.mobileproject.appuntil.AppUntil;
import com.example.mobileproject.datamodel.ChuTro;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MotelRoomOwnerFragment extends AbstractFragment{
    RecyclerView recyclerView;
    List<ChuTro> list;
    ChuTroAdapter chuTroAdapter;
    LinearLayoutManager layoutManager;

    EditText edtTimKiemChuTro;
    Button btnTimKiemChuTro;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentLayout = null;
        fragmentLayout = inflater.inflate(R.layout.fragment_admin_motel_room_owner_layout, container, false);
        edtTimKiemChuTro = fragmentLayout.findViewById(R.id.edtTimKiemChuTro);
        btnTimKiemChuTro = fragmentLayout.findViewById(R.id.btnTimKiemChuTro);
        recyclerView = fragmentLayout.findViewById(R.id.rvChuTro);
        layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(chuTroAdapter);
        list = new ArrayList<>();

        ListMotelRoomOwnerAPI();

        btnTimKiemChuTro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtTimKiemChuTro.getText() != null)
                {
                    String key = String.valueOf(edtTimKiemChuTro.getText());
                    FindMotelRoomOwnerByNameAPI(key);
                    FindMotelRoomOwnerByPhoneAPI(key);
                }
                else
                {
                    ListMotelRoomOwnerAPI();
                }
            }
        });

        return fragmentLayout;
    }
    private void ListMotelRoomOwnerAPI()
    {
        ApiServiceKiet.apiServiceKiet.getListHostAPI().enqueue(new Callback<List<ChuTro>>() {
            @Override
            public void onResponse(Call<List<ChuTro>> call, Response<List<ChuTro>> response) {
                list = response.body();
                chuTroAdapter = new ChuTroAdapter(getActivity(), list, R.layout.cardview_admin_motel_room_owner_layout);
                recyclerView.setAdapter(chuTroAdapter);
                chuTroAdapter.setOnClickItemListener(new ChuTroAdapter.OnClickItemListener() {
                    @Override
                    public void onClickItem(int position, View v) {
                        Log.d("RESULT", list.get(position)+"");
                        AppUntil.ID_CHU_TRO = list.get(position).getIdTaiKhoan();
                        nextActivity();
                    }
                });
            }

            @Override
            public void onFailure(Call<List<ChuTro>> call, Throwable t) {

            }
        });
    }

    private void FindMotelRoomOwnerByNameAPI(String key)
    {
        ApiServiceKiet.apiServiceKiet.getHostByNameAPI(key).enqueue(new Callback<List<ChuTro>>() {
            @Override
            public void onResponse(Call<List<ChuTro>> call, Response<List<ChuTro>> response) {
                list = response.body();
                chuTroAdapter = new ChuTroAdapter(getActivity(), list, R.layout.cardview_admin_motel_room_owner_layout);
                recyclerView.setAdapter(chuTroAdapter);
                chuTroAdapter.setOnClickItemListener(new ChuTroAdapter.OnClickItemListener() {
                    @Override
                    public void onClickItem(int position, View v) {
                        Log.d("RESULT", list.get(position)+"");
                        AppUntil.ID_CHU_TRO = list.get(position).getIdTaiKhoan();
                        nextActivity();
                    }
                });
            }

            @Override
            public void onFailure(Call<List<ChuTro>> call, Throwable t) {

            }
        });
    }
    private void FindMotelRoomOwnerByPhoneAPI(String key)
    {
        ApiServiceKiet.apiServiceKiet.getHostByPhoneAPI(key).enqueue(new Callback<List<ChuTro>>() {
            @Override
            public void onResponse(Call<List<ChuTro>> call, Response<List<ChuTro>> response) {
                list = response.body();
                chuTroAdapter = new ChuTroAdapter(getActivity(), list, R.layout.cardview_admin_motel_room_owner_layout);
                recyclerView.setAdapter(chuTroAdapter);
                chuTroAdapter.setOnClickItemListener(new ChuTroAdapter.OnClickItemListener() {
                    @Override
                    public void onClickItem(int position, View v) {
                        Log.d("RESULT", list.get(position)+"");
                        AppUntil.ID_CHU_TRO = list.get(position).getIdTaiKhoan();
                        nextActivity();
                    }
                });
            }

            @Override
            public void onFailure(Call<List<ChuTro>> call, Throwable t) {

            }
        });
    }

    private void nextActivity()
    {
        Intent intent = new Intent(getActivity(), MotelRoomOwnerDetailActivity.class);
        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
        ListMotelRoomOwnerAPI();
    }
}
