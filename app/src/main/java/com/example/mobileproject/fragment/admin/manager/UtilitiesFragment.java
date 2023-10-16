package com.example.mobileproject.fragment.admin.manager;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileproject.R;
import com.example.mobileproject.activity.admin.AddUtilitiesActivity;
import com.example.mobileproject.activity.admin.EditUtilitiesActivity;
import com.example.mobileproject.adapter.admin.TienIchAdapter;
import com.example.mobileproject.api.admin.ApiServiceNghiem;
import com.example.mobileproject.datamodel.TienIch;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UtilitiesFragment extends AbstractFragment {

    ArrayList<TienIch> arrayList;
    RecyclerView recyclerView;
    TienIchAdapter tienIchAdapter;
    LinearLayoutManager layoutManager;
    FloatingActionButton fabAdd;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentLayout = null;
        fragmentLayout = inflater.inflate(R.layout.fragment_admin_management_utilities_layout, container, false);
        anhXa(fragmentLayout);
        setDuLieu();
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getContext(), AddUtilitiesActivity.class);
                startActivity(intent);
            }
        });
        return fragmentLayout;
    }
    private void setDuLieu(){
        arrayList = new ArrayList<>();
        tienIchAdapter = new TienIchAdapter(arrayList,getActivity(),R.layout.cardview_admin_utilities,R.layout.cardview_admin_utilities_block);
        layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(tienIchAdapter);

        Call<ArrayList<TienIch>> call = ApiServiceNghiem.apiService.layTatCaTienIch();
        call.enqueue(new Callback<ArrayList<TienIch>>() {
            @Override
            public void onResponse(Call<ArrayList<TienIch>> call, Response<ArrayList<TienIch>> response) {
                if(response.isSuccessful()){
                    for (TienIch tienIch: response.body()) {
                            arrayList.add(tienIch);
                    }
                }
                tienIchAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ArrayList<TienIch>> call, Throwable t) {

            }
        });
        tienIchAdapter.setOnClickListener(new TienIchAdapter.OnClickListener() {
            @Override
            public void onClickSua(int position, View view) {


                Intent intent = new Intent(getContext(), EditUtilitiesActivity.class);
                intent.putExtra("key",arrayList.get(position).getId());
                startActivity(intent);
            }
        });
    }
    private void anhXa(View fragment){
        recyclerView = fragment.findViewById(R.id.recycleView);
        fabAdd = fragment.findViewById(R.id.fabAdd);
    }
}
