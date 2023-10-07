package com.example.mobileproject.fragment.admin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.mobileproject.R;
import com.example.mobileproject.datamodel.Banner;
import com.example.mobileproject.recycerviewadapter.admin.BannerAdapter;
import com.example.mobileproject.viewpageradapter.admin.ManagementViewPager2Adapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class ManagementFragment extends AbstractFragment {

    private TabLayout mTablayout;
    private ViewPager2 mViewPager2;
    private ManagementViewPager2Adapter mViewPager2Adapter;

    private RecyclerView rcvBanner;
    private BannerAdapter bannerAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentLayout = null;
        fragmentLayout = inflater.inflate(R.layout.fragment_admin_management_layout, container, false);

        rcvBanner = fragmentLayout.findViewById(R.id.rcvBanner);
        bannerAdapter = new BannerAdapter(getActivity(), getListBanner());


        mTablayout = fragmentLayout.findViewById(R.id.tab_layout_manager);
        mViewPager2 = fragmentLayout.findViewById(R.id.view_pager2_manager);

        mViewPager2Adapter = new ManagementViewPager2Adapter(getActivity());
        mViewPager2.setAdapter(mViewPager2Adapter);

        new TabLayoutMediator(mTablayout, mViewPager2, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText("Tiện ích");
                    break;
                case 1:
                    tab.setText("Quận");
                    break;
                case 2:
                    tab.setText("Hình ảnh");
                    break;
            }
        }).attach();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        rcvBanner.setLayoutManager(linearLayoutManager);

        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        rcvBanner.addItemDecoration(itemDecoration);

        rcvBanner.setAdapter(bannerAdapter);




        return fragmentLayout;

    }

    private List<Banner> getListBanner() {
        List<Banner> list = new ArrayList<>();
        list.add(new Banner(1, R.drawable.banner1, "Hinh 1"));
        list.add(new Banner(2, R.drawable.banner2, "Hinh 2"));
        list.add(new Banner(3, R.drawable.banner3, "Hinh 3"));
        list.add(new Banner(4, R.drawable.banner4, "Hinh 4"));

        return list;
    }


}
