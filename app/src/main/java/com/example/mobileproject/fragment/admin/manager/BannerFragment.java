package com.example.mobileproject.fragment.admin.manager;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.mobileproject.R;
import com.example.mobileproject.datamodel.Banner;
import com.example.mobileproject.recycerviewadapter.admin.BannerAdapter;
import com.example.mobileproject.viewpageradapter.admin.ManagementViewPager2Adapter;

import java.util.LinkedList;
import java.util.List;

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
        dlg();
        adapter = new BannerAdapter(getActivity(), listIem, R.layout.cardview_admin_management_banner_layout);
        layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        rcvBanner.setLayoutManager(layoutManager);
        rcvBanner.setAdapter(adapter);


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
    private void dlg(){
        listIem.add(new Banner(1,R.drawable.banner4,"cdsfxc"));
        listIem.add(new Banner(1,1,"cdsfxc"));
        listIem.add(new Banner(1,1,"cdsfxc"));
        listIem.add(new Banner(1,1,"cdsfxc"));
        listIem.add(new Banner(1,1,"cdsfxc"));
        listIem.add(new Banner(1,1,"cdsfxc"));
        listIem.add(new Banner(1,1,"cdsfxc"));
    }
}
