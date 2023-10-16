package com.example.mobileproject.fragment.admin.manager;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileproject.R;
import com.example.mobileproject.activity.admin.AddDistrictActivity;
import com.example.mobileproject.activity.admin.EditDistrictActivity;
import com.example.mobileproject.activity.admin.ListWardActivity;
import com.example.mobileproject.adapter.admin.QuanAdapter;
import com.example.mobileproject.datamodel.Quan;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

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
        arrayList.add(new Quan(1,"Quận Thủ Đức", "hinh1",0));
        arrayList.add(new Quan(2,"Quận Thủ Đức", "hinh1",0));
        arrayList.add(new Quan(3,"Quận Thủ Đức", "hinh1",0));
        arrayList.add(new Quan(4,"Quận Thủ Đức", "hinh1",0));
        quanAdapter= new QuanAdapter(arrayList,getActivity(),R.layout.cardview_admin_district);
        linearLayout = new LinearLayoutManager(getActivity());
        linearLayout.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayout);
        recyclerView.setAdapter(quanAdapter);
    }
    private void anhXa(View fragment){
        fabAdd = fragment.findViewById(R.id.fabAdd);
        recyclerView = fragment.findViewById(R.id.recycleView);
    }

}
