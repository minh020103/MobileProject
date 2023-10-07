package com.example.mobileproject.fragment.admin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.mobileproject.R;
import com.example.mobileproject.viewpageradapter.admin.ManagementViewPager2Adapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class ManagementFragment extends AbstractFragment{

    private TabLayout mTablayout;
    private ViewPager2 mViewPager2;
    private ManagementViewPager2Adapter mViewPager2Adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentLayout = null;
        fragmentLayout = inflater.inflate(R.layout.fragment_admin_management_layout, container, false);

        mTablayout = fragmentLayout.findViewById(R.id.tab_layout_manager);
        mViewPager2 = fragmentLayout.findViewById(R.id.view_pager2_manager);

        mViewPager2Adapter = new ManagementViewPager2Adapter(getActivity());
        mViewPager2.setAdapter(mViewPager2Adapter);

        new TabLayoutMediator(mTablayout, mViewPager2, (tab, position) -> {
            switch (position){
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
        return fragmentLayout;
    }


}
