package com.example.mobileproject.fragment.admin.manager;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileproject.R;
import com.example.mobileproject.activity.admin.AddDistrictActivity;
import com.example.mobileproject.activity.admin.EditDistrictActivity;
import com.example.mobileproject.activity.admin.EditUtilitiesActivity;
import com.example.mobileproject.activity.admin.ListWardActivity;
import com.example.mobileproject.adapter.admin.QuanAdapter;
import com.example.mobileproject.api.admin.ApiServiceDung;
import com.example.mobileproject.api.admin.ApiServiceNghiem;
import com.example.mobileproject.datamodel.Quan;
import com.example.mobileproject.datamodel.TienIch;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DistrictFragment extends AbstractFragment {

    ArrayList<Quan> arrayList;
    QuanAdapter quanAdapter;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayout;
    FloatingActionButton fabAdd;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentLayout = null;
        fragmentLayout = inflater.inflate(R.layout.fragment_admin_management_district_layout, container, false);
        anhXa(fragmentLayout);
        setDuLieu();
        setSuKien();
        return fragmentLayout;
    }
    private void setSuKien(){
        quanAdapter.setOnClickListener(new QuanAdapter.OnClickListener() {
            @Override
            public void onClickXem(int position, View view) {
                Intent intent = new Intent(getContext(), ListWardActivity.class);
                startActivity(intent);
            }

            @Override
            public void onClickSua(int position, View view) {
                Intent intent = new Intent(getContext(), EditDistrictActivity.class);
                startActivity(intent);
            }
        });
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), AddDistrictActivity.class);
                startActivity(intent);
            }
        });
    }
    private void setDuLieu(){
        arrayList = new ArrayList<>();
        quanAdapter= new QuanAdapter(arrayList,getActivity(),R.layout.cardview_admin_district,R.layout.cardview_admin_district_block);
        linearLayout = new LinearLayoutManager(getActivity());
        linearLayout.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayout);
        recyclerView.setAdapter(quanAdapter);
        Call<ArrayList<Quan>> call = ApiServiceDung.apiServiceDung.layTatCaQuan();
        call.enqueue(new Callback<ArrayList<Quan>>() {
            @Override
            public void onResponse(Call<ArrayList<Quan>> call, Response<ArrayList<Quan>> response) {
                if(response.isSuccessful()){
                    arrayList.addAll(response.body());
                    quanAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Quan>> call, Throwable t) {

            }
        });


        quanAdapter.setOnClickListener(new QuanAdapter.OnClickListener() {
            @Override
            public void onClickXem(int position, View view) {
                Intent intent = new Intent(getContext(), ListWardActivity.class);
                intent.putExtra("idQuan",arrayList.get(position).getId());
                intent.putExtra("nameQuan",arrayList.get(position).getTenQuan());
                startActivity(intent);
            }

            @Override
            public void onClickSua(int position, View view) {
                Intent intent = new Intent(getContext(), EditDistrictActivity.class);
                intent.putExtra("key",arrayList.get(position).getId());
                startActivity(intent);
            }
        });
    }
    private void anhXa(View fragment){
        fabAdd = fragment.findViewById(R.id.fabAdd);
        recyclerView = fragment.findViewById(R.id.recycleView);

    }

    @Override
    public void onResume() {
        super.onResume();
        setDuLieu();
    }
}
