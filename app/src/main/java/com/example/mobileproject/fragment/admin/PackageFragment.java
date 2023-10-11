package com.example.mobileproject.fragment.admin;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileproject.R;
import com.example.mobileproject.activity.admin.PakageDetailActivity;
import com.example.mobileproject.adapter.admin.GoiDichVuAdapter;
import com.example.mobileproject.api.admin.ApiServiceKiet;
import com.example.mobileproject.appuntil.AppUntil;
import com.example.mobileproject.datamodel.GoiDichVu;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PackageFragment extends AbstractFragment{
    RecyclerView recyclerView;
    List<GoiDichVu> list;
    GoiDichVuAdapter goiDichVuAdapter;
    LinearLayoutManager layoutManager;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentLayout = null;
        fragmentLayout = inflater.inflate(R.layout.fragment_admin_package_layout, container, false);

        recyclerView = fragmentLayout.findViewById(R.id.rvGoiDichVu);
        layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(goiDichVuAdapter);
        list = new ArrayList<>();

        ListPakageAPI();

        return fragmentLayout;
    }

    private void ListPakageAPI()
    {
        ApiServiceKiet.apiServiceKiet.getListPakageAPI().enqueue(new Callback<List<GoiDichVu>>() {
            @Override
            public void onResponse(Call<List<GoiDichVu>> call, Response<List<GoiDichVu>> response) {
                list = response.body();
                goiDichVuAdapter = new GoiDichVuAdapter(getActivity(), list, R.layout.cardview_admin_package_layout);
                recyclerView.setAdapter(goiDichVuAdapter);
                goiDichVuAdapter.setOnClickItemListener(new GoiDichVuAdapter.OnClickItemListener() {
                    @Override
                    public void onClickItem(int position, View v) {
                        Log.d("TAG", list.get(position) + "");
                        AppUntil.ID_GOI_DICH_VU = list.get(position).getId();
                        nextActivity();
                    }
                });
            }

            @Override
            public void onFailure(Call<List<GoiDichVu>> call, Throwable t) {

            }
        });
    }

    private void nextActivity()
    {
        Intent intent = new Intent(getActivity(), PakageDetailActivity.class);
        startActivity(intent);
    }
}
