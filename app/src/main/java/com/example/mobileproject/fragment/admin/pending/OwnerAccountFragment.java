package com.example.mobileproject.fragment.admin.pending;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileproject.R;
import com.example.mobileproject.RecyclerViewAdapter.OwnerAccountAdapter;
import com.example.mobileproject.datamodel.ChuTro;

import java.util.ArrayList;
import java.util.List;

public class OwnerAccountFragment extends AbstractFragment {
    private RecyclerView rcvAccountOwner;
    private OwnerAccountAdapter adapter;
    private LinearLayoutManager layoutManager;
    private List<ChuTro> listChuTro;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentLayout = null;
        fragmentLayout = inflater.inflate(R.layout.fragment_admin_pending_account_owner_layout, container, false);

        anhXa(fragmentLayout);

        suLi();

        return fragmentLayout;
    }

    private void suLi() {
        listChuTro.add(new ChuTro(1,1,"fds","Nguyễn Đức Minh","0123456789",1,"012345546561451","NGUYEN DUC MINH",0));
        listChuTro.add(new ChuTro(2,2,"fds","Nguyễn Đức Minh","0123456789",1,"012345546561451","NGUYEN DUC MINH",0));
        listChuTro.add(new ChuTro(3,3,"fds","Nguyễn Đức Minh","0123456789",1,"012345546561451","NGUYEN DUC MINH",0));
        listChuTro.add(new ChuTro(4,2,"fds","Nguyễn Đức Minh","0123456789",1,"012345546561451","NGUYEN DUC MINH",0));
        listChuTro.add(new ChuTro(5,3,"fds","Nguyễn Đức Minh","0123456789",1,"012345546561451","NGUYEN DUC MINH",0));
        listChuTro.add(new ChuTro(6,2,"fds","Nguyễn Đức Minh","0123456789",1,"012345546561451","NGUYEN DUC MINH",0));
        listChuTro.add(new ChuTro(7,3,"fds","Nguyễn Đức Minh","0123456789",1,"012345546561451","NGUYEN DUC MINH",0));
        listChuTro.add(new ChuTro(8,2,"fds","Nguyễn Đức Minh","0123456789",1,"012345546561451","NGUYEN DUC MINH",0));
        listChuTro.add(new ChuTro(9,3,"fds","Nguyễn Đức Minh","0123456789",1,"012345546561451","NGUYEN DUC MINH",0));
        adapter = new OwnerAccountAdapter(getActivity(), R.layout.cardview_admin_pending_owner_account_layout, listChuTro);
        layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        rcvAccountOwner.setLayoutManager(layoutManager);
        rcvAccountOwner.setAdapter(adapter);

    }

    private void anhXa(View fragmentLayout) {
        rcvAccountOwner = fragmentLayout.findViewById(R.id.rcvAccountOwner);
        listChuTro = new ArrayList<>();

    }
}
