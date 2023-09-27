package com.example.mobileproject.ViewPagerAdaper;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.mobileproject.fragment.admin.ungdung.HinhAnhFragment;
import com.example.mobileproject.fragment.admin.ungdung.KhuVucFragment;
import com.example.mobileproject.fragment.admin.ungdung.TienIchFragment;

import java.util.ArrayList;

public class VPUngDungAdapter extends FragmentStateAdapter {


    public VPUngDungAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment = new TienIchFragment();
        switch (position){
            case 0:
                fragment = new TienIchFragment();
                break;
            case 1:
                fragment = new KhuVucFragment();
                break;
            case 2:
                fragment = new HinhAnhFragment();
                break;
        }
        return fragment;
    }

    @Override
    public int getItemCount() {
        return 3;
    }


}
