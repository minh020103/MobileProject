package com.example.mobileproject.fragment.admin.ungdung;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.mobileproject.R;
import com.example.mobileproject.fragment.admin.AbstractFragment;

public class HinhAnhFragment extends AbstractFragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentLayout = null;
        fragmentLayout = inflater.inflate(R.layout.fragment_admin_ungdung_hinhanh_layout, container, false);


        return fragmentLayout;
    }
}
