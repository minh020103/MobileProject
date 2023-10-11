package com.example.mobileproject.viewpageradapter.admin;

import android.app.Activity;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.mobileproject.fragment.admin.manager.AbstractFragment;
import com.example.mobileproject.fragment.admin.ManagementFragment;
import com.example.mobileproject.fragment.admin.MotelRoomOwnerFragment;
import com.example.mobileproject.fragment.admin.PackageFragment;
import com.example.mobileproject.fragment.admin.PendingFragment;
import com.example.mobileproject.fragment.admin.ProfileFragment;
import com.example.mobileproject.fragment.admin.manager.BannerFragment;
import com.example.mobileproject.fragment.admin.manager.DistrictFragment;
import com.example.mobileproject.fragment.admin.manager.UtilitiesFragment;

public class ManagementViewPager2Adapter extends FragmentStateAdapter {

    private final int UTILITIES_SCREEN = 0;
    private final int DISTRICT_SCREEN = 1;
    private final int BANNER_SCREEN = 2;

    AbstractFragment fragment;
    FragmentManager fragmentManager;

    FragmentActivity fragmentActivity;
    public ManagementViewPager2Adapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
        this.fragmentActivity = fragmentActivity;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return replaceFragment(position);
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public Fragment replaceFragment(int screenID){
        fragmentManager = fragmentActivity.getSupportFragmentManager();
        if (fragmentManager.findFragmentByTag(screenID + "") != null) {
            fragment = (AbstractFragment) fragmentManager.findFragmentByTag(screenID + "");
        } else {
            switch (screenID){
                case UTILITIES_SCREEN:
                    fragment = new UtilitiesFragment();
                    break;
                case DISTRICT_SCREEN:
                    fragment = new DistrictFragment();
                    break;
                case BANNER_SCREEN:
                    fragment = new BannerFragment();
                    break;
                default:
                    fragment = new UtilitiesFragment();
                    break;
            }
        }
        return fragment;
    }
}
