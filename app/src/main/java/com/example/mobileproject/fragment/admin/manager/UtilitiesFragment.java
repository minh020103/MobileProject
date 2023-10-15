package com.example.mobileproject.fragment.admin.manager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileproject.R;
import com.example.mobileproject.adapter.admin.TienIchAdapter;
import com.example.mobileproject.datamodel.TienIch;

import java.util.ArrayList;

public class UtilitiesFragment extends AbstractFragment {

    ArrayList<TienIch> arrayList;
    RecyclerView recyclerView;
    TienIchAdapter tienIchAdapter;
    LinearLayoutManager layoutManager;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentLayout = null;
        fragmentLayout = inflater.inflate(R.layout.fragment_admin_management_utilities_layout, container, false);
        anhXa(fragmentLayout);
        setDuLieu();
        return fragmentLayout;
    }
    private void setDuLieu(){
        arrayList = new ArrayList<>();
        arrayList.add(new TienIch("Máy Giặt","máy giặt",1));
        arrayList.add(new TienIch("Máy Giặt","máy giặt",1));
        arrayList.add(new TienIch("Máy Giặt","máy giặt",1));
        arrayList.add(new TienIch("Máy Giặt","máy giặt",1));
        arrayList.add(new TienIch("Máy Giặt","máy giặt",1));
        arrayList.add(new TienIch("Máy Giặt","máy giặt",1));
        arrayList.add(new TienIch("Máy Giặt","máy giặt",1));
        arrayList.add(new TienIch("Máy Giặt","máy giặt",1));
        arrayList.add(new TienIch("Máy Giặt","máy giặt",1));
        arrayList.add(new TienIch("Máy Giặt","máy giặt",1));
        arrayList.add(new TienIch("Máy Giặt","máy giặt",1));
        arrayList.add(new TienIch("Máy Giặt","máy giặt",1));
        arrayList.add(new TienIch("Máy Giặt","máy giặt",1));
        arrayList.add(new TienIch("Máy Giặt","máy giặt",1));
        arrayList.add(new TienIch("Máy Giặt","máy giặt",1));
        arrayList.add(new TienIch("Máy Giặt","máy giặt",1));
        arrayList.add(new TienIch("Máy Giặt","máy giặt",1));
        arrayList.add(new TienIch("Máy Giặt","máy giặt",1));
        tienIchAdapter = new TienIchAdapter(arrayList,getActivity(),R.layout.cardview_admin_utilities);
        layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(tienIchAdapter);
    }
    private void anhXa(View fragment){
        recyclerView = fragment.findViewById(R.id.recycleView);
    }
}
