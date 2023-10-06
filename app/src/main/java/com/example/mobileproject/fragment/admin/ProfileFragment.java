package com.example.mobileproject.fragment.admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

import com.example.mobileproject.R;
import com.example.mobileproject.activity.admin.EditPolicyActivity;

public class ProfileFragment extends AbstractFragment{

    AppCompatImageView nextChinhSach;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentLayout = null;
        fragmentLayout = inflater.inflate(R.layout.fragment_admin_profile_layout, container, false);
        anhXa(fragmentLayout);
        nextChinhSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), EditPolicyActivity.class);
                startActivity(intent);
            }
        });

        return fragmentLayout;
    }
    private void anhXa(View fragment){
        nextChinhSach = fragment.findViewById(R.id.nextDoiChinhSach);
    }
}
