package com.example.mobileproject.fragment.admin.manager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileproject.R;
import com.example.mobileproject.api.admin.ApiServivePhuc;
import com.example.mobileproject.datamodel.Banner;
import com.example.mobileproject.recycerviewadapter.admin.BannerAdapter;

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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentLayout = null;
        fragmentLayout = inflater.inflate(R.layout.fragment_admin_management_banner_layout, container, false);
        rcvBanner = fragmentLayout.findViewById(R.id.rcvBanner);
        listIem = new LinkedList<>();
        adapter = new BannerAdapter(getActivity(), listIem, R.layout.cardview_admin_management_banner_layout);
        layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        rcvBanner.setLayoutManager(layoutManager);
        rcvBanner.setAdapter(adapter);

        getDataFromApi();
        setDataForUI();


        adapter.setMyOnCLickListener(new BannerAdapter.MyOnCLickListener() {
            @Override
            public void OnClickItem(int position, View v) {

            }

            @Override
            public void OnClickFabAdd(int position, View v) {

            }
        });

        return fragmentLayout;
    }

    private void setDataForUI() {
    }

    private void getDataFromApi() {
        ApiServivePhuc.apiService.getListBanner().enqueue(new Callback<List<Banner>>() {
            @Override
            public void onResponse(Call<List<Banner>> call, Response<List<Banner>> response) {
                if (response.code() == 200){
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
