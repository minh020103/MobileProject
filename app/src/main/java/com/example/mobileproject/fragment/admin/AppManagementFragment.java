package com.example.mobileproject.fragment.admin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import com.example.mobileproject.R;
import com.example.mobileproject.ViewPagerAdaper.VPUngDungAdapter;
import com.example.mobileproject.fragment.admin.ungdung.HinhAnhFragment;
import com.example.mobileproject.fragment.admin.ungdung.KhuVucFragment;
import com.example.mobileproject.fragment.admin.ungdung.TienIchFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class AppManagementFragment extends AbstractFragment{

    private TabLayout tlUngDung;
    private ViewPager2 vp2UngDung;
    private VPUngDungAdapter adapter;
    private FragmentManager fragmentManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentLayout = null;
        fragmentLayout = inflater.inflate(R.layout.fragment_admin_app_management_layout, container, false);

        tlUngDung = fragmentLayout.findViewById(R.id.tlUngUng);
        vp2UngDung = fragmentLayout.findViewById(R.id.vpUngDung);

        adapter = new VPUngDungAdapter(getActivity());
        vp2UngDung.setAdapter(adapter);

        new TabLayoutMediator(tlUngDung, vp2UngDung, (tab, position) -> {
            switch (position){
                case 0:
                    tab.setText("Tiện ích");
                    break;
                case 1:
                    tab.setText("Khu vực");
                    break;
                case 2:
                    tab.setText("Hình ảnh");
                    break;
            }
        }).attach();

        requireActivity().getOnBackPressedDispatcher().addCallback(){

        };


        return fragmentLayout;
    }
}
