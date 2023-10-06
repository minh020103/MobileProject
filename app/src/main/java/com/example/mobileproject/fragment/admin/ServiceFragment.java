package com.example.mobileproject.fragment.admin;

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
import com.example.mobileproject.adapter.admin.HostAdapter;
import com.example.mobileproject.adapter.admin.ServiceAdapter;
import com.example.mobileproject.apiKiet.ApiServiceKiet;
import com.example.mobileproject.model.HostModel;
import com.example.mobileproject.model.ServiceModel;
import com.example.mobileproject.until.AppUtil;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ServiceFragment extends AbstractFragment{

    RecyclerView recyclerView;
    List<ServiceModel> list;
    ServiceAdapter serviceAdapter;
    LinearLayoutManager layoutManager;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentLayout = null;
        fragmentLayout = inflater.inflate(R.layout.fragment_admin_service_layout, container, false);

        recyclerView = fragmentLayout.findViewById(R.id.rvService);
        layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(serviceAdapter);

        list = new ArrayList<>();

        ListServiceApi();

        return fragmentLayout;
    }

    private void ListServiceApi()
    {
        ApiServiceKiet.apiServiceKiet.getListServiceAPI().enqueue(new Callback<List<ServiceModel>>() {
            @Override
            public void onResponse(Call<List<ServiceModel>> call, Response<List<ServiceModel>> response) {
                Log.d("tinnhan", "thanh cong");
                //List<HostModel> list = response.body();
                list = response.body();
                serviceAdapter = new ServiceAdapter(getActivity(),list, R.layout.cardview_admin_service_layout);
                recyclerView.setAdapter(serviceAdapter);

            }

            @Override
            public void onFailure(Call<List<ServiceModel>> call, Throwable t) {
                Log.d("tinnhan", "that bai");
            }
        });

    }

    private int GetPositionId(int Id)
    {
        return Id;
    }
}
