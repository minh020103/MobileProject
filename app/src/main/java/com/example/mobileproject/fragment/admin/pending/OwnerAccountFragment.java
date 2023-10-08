package com.example.mobileproject.fragment.admin.pending;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileproject.R;
import com.example.mobileproject.activity.admin.InfomationConfirmMotelRoomOwner;
import com.example.mobileproject.recycerviewadapter.admin.PendingOwnerAccountAdapter;
import com.example.mobileproject.datamodel.ChuTro;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class OwnerAccountFragment extends AbstractFragment {
    private RecyclerView rcvAccountOwner;
    private PendingOwnerAccountAdapter adapter;
    private LinearLayoutManager layoutManager;
    private List<ChuTro> listChuTro;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentLayout = null;
        fragmentLayout = inflater.inflate(R.layout.fragment_admin_pending_account_owner_layout, container, false);


        anhXa(fragmentLayout);
        suLi();


        adapter.setOnClickItemListener(new PendingOwnerAccountAdapter.OnClickItemListener() {
            @Override
            public void onClick(int position, View view) {
                Intent intent = new Intent(getActivity(), InfomationConfirmMotelRoomOwner.class);
                startActivity(intent);
            }
        });

        return fragmentLayout;
    }

    private void suLi() {
        listChuTro.add(new ChuTro(1,1,"jj","add","0345","asifoier",1,"hjjwtu","uegiurigu", 1));
        adapter = new PendingOwnerAccountAdapter(getActivity(), R.layout.cardview_admin_pending_owner_account_layout, listChuTro);
        layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        rcvAccountOwner.setLayoutManager(layoutManager);
        rcvAccountOwner.setAdapter(adapter);

    }

    private void anhXa(View fragmentLayout) {
        rcvAccountOwner = fragmentLayout.findViewById(R.id.rcvAccountOwner);
        listChuTro = new LinkedList<>();

    }
}
