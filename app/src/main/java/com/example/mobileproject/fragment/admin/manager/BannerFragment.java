package com.example.mobileproject.fragment.admin.manager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileproject.R;
import com.example.mobileproject.activity.admin.AddBannerActivity;
import com.example.mobileproject.activity.admin.Edit_Delete_BannerActivity;
import com.example.mobileproject.api.admin.ApiServivePhuc;
import com.example.mobileproject.datamodel.Banner;
import com.example.mobileproject.recycerviewadapter.admin.BannerAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.LinkedList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BannerFragment extends AbstractFragment {

    BannerAdapter adapter;
    LinearLayoutManager layoutManager;
    RecyclerView rcvBanner;
    List<Banner> listIem;
    FloatingActionButton btnFabAdd;
    Handler handler;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentLayout = null;
        fragmentLayout = inflater.inflate(R.layout.fragment_admin_management_banner_layout, container, false);
        rcvBanner = fragmentLayout.findViewById(R.id.rcvBanner);
        btnFabAdd = fragmentLayout.findViewById(R.id.btn_fabAdd);
        listIem = new LinkedList<>();
        adapter = new BannerAdapter(getActivity(), listIem, R.layout.cardview_admin_management_banner_layout);
        layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        rcvBanner.setLayoutManager(layoutManager);
        rcvBanner.setAdapter(adapter);
        getDataFromApi();

        adapter.setMyOnCLickListener(new BannerAdapter.MyOnCLickListener() {
            @Override
            public void OnClickItem(int position, View v) {

                Intent intent = new Intent(getActivity(), Edit_Delete_BannerActivity.class);
                intent.putExtra("hinh",listIem.get(position).getHinhBanner()+ "");
                intent.putExtra("id",listIem.get(position).getId());
                startActivity(intent);
            }
        });

        btnFabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddBannerActivity.class);
                startActivity(intent);
            }
        });
        return fragmentLayout;
    }

    @Override
    public void onResume() {
        super.onResume();

        getDataFromApi();

        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                getDataFromApi();
                handler.postDelayed(this, 3000);
            }
        }, 3000);
    }

    private void getDataFromApi() {
        ApiServivePhuc.apiService.getListBanner().enqueue(new Callback<List<Banner>>() {
            @Override
            public void onResponse(Call<List<Banner>> call, Response<List<Banner>> response) {
                if (response.code() == 200){
                    listIem.clear();
                    listIem.addAll(response.body());
                    adapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onFailure(Call<List<Banner>> call, Throwable t) {
            }
        });
    }

}
