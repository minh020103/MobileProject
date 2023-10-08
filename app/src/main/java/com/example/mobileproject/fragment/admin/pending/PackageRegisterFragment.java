package com.example.mobileproject.fragment.admin.pending;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileproject.R;
import com.example.mobileproject.activity.admin.InfomationConfirmMotelRoomOwner;
import com.example.mobileproject.activity.admin.InfomationPackageRegisterActivity;
import com.example.mobileproject.datamodel.DangKyDichVu;
import com.example.mobileproject.recycerviewadapter.admin.PendingPackageAdapter;

import java.util.LinkedList;
import java.util.List;

public class PackageRegisterFragment extends AbstractFragment {

    private RecyclerView rcvPandingPackage;
    private PendingPackageAdapter adapter;
    private LinearLayoutManager layoutManager;
    private List<DangKyDichVu> list;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentLayout = null;
        fragmentLayout = inflater.inflate(R.layout.fragment_admin_pending_package_register_layout, container, false);

        anhXa(fragmentLayout);
        clickItem();

        return fragmentLayout;
    }

    private void clickItem() {
        adapter.setOnClick(new PendingPackageAdapter.OnClick() {
            @Override
            public void onClickItemListenner(int position, View v) {
                Intent intent = new Intent(getActivity(), InfomationPackageRegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    private void anhXa(View fragmentLayout) {
        list = new LinkedList<>();
        list.add(new DangKyDichVu(1,1,1,"30/12/2023",1));
        list.add(new DangKyDichVu(1,1,1,"30/12/2023",1));
        list.add(new DangKyDichVu(1,1,1,"30/12/2023",1));
        list.add(new DangKyDichVu(1,1,1,"30/12/2023",1));
        list.add(new DangKyDichVu(1,1,1,"30/12/2023",1));
        list.add(new DangKyDichVu(1,1,1,"30/12/2023",1));
        list.add(new DangKyDichVu(1,1,1,"30/12/2023",1));
        list.add(new DangKyDichVu(1,1,1,"30/12/2023",1));
        list.add(new DangKyDichVu(1,1,1,"30/12/2023",1));
        list.add(new DangKyDichVu(1,1,1,"30/12/2023",1));

        rcvPandingPackage = fragmentLayout.findViewById(R.id.rcvPendingPackage);
        adapter = new PendingPackageAdapter(getActivity(), list, R.layout.cardview_admin_pending_package_register_layout);
        layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        rcvPandingPackage.setLayoutManager(layoutManager);
        rcvPandingPackage.setAdapter(adapter);
    }
}
