package com.example.mobileproject.fragment.admin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager2.widget.ViewPager2;

import com.example.mobileproject.R;
import com.example.mobileproject.ViewPagerAdapter.amin.VPPendingAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class PendingFragment extends AbstractFragment{


    private TabLayout tlPending;
    private ViewPager2 vp2Pending;
    private VPPendingAdapter adapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentLayout = null;
        fragmentLayout = inflater.inflate(R.layout.fragment_admin_pending_layout, container, false);

        anhXa(fragmentLayout);

        suLi();


        return fragmentLayout;
    }

    private void suLi() {
        vp2Pending.setAdapter(adapter);



        new TabLayoutMediator(tlPending, vp2Pending, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position){
                    case 0:
                        tab.setText("Tài khoản");
                        break;
                    case 1:
                        tab.setText("Gói dịch vụ");
                        break;
                    case 2:
                        tab.setText("Xóa phòng");
                        break;
                }
            }
        }).attach();
    }

    private void anhXa(View fragmentLayout) {
        tlPending = fragmentLayout.findViewById(R.id.tlChoDuyet);
        vp2Pending = fragmentLayout.findViewById(R.id.vp2ChoDuyet);
        adapter = new VPPendingAdapter(getActivity());
    }
}
