package com.example.mobileproject.viewpageradapter.admin;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.mobileproject.fragment.admin.manager.BannerFragment;
import com.example.mobileproject.fragment.admin.manager.DistrictFragment;
import com.example.mobileproject.fragment.admin.manager.UtilitiesFragment;

public class ManagementViewPager2Adapter extends FragmentStateAdapter {


    public ManagementViewPager2Adapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new UtilitiesFragment();
            case 1:
                return new DistrictFragment();
            case 2:
                return new BannerFragment();
            default:
                return new UtilitiesFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
