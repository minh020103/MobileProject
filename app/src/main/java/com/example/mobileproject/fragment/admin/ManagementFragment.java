package com.example.mobileproject.fragment.admin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileproject.R;
import com.example.mobileproject.adapter.admin.ManagerAdapter;
import com.example.mobileproject.model.ManagerModel;

import java.util.ArrayList;
import java.util.List;

public class ManagementFragment extends AbstractFragment{

    RecyclerView recyclerView;
    List<ManagerModel> list;
    ManagerAdapter managerAdapter;
    LinearLayoutManager layoutManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentLayout = null;
        fragmentLayout = inflater.inflate(R.layout.fragment_admin_management_layout, container, false);


        /// code
        list = new ArrayList<>();
        list.add(new ManagerModel(1, "Kiet", "sldfje", "0987123456", "TP.HCM" , 50000));
        list.add(new ManagerModel(2, "Kiet", "sldfje", "0987123456", "TP.HCM" , 50000));
        list.add(new ManagerModel(3, "Kiet", "sldfje", "0987123456", "TP.HCM" , 50000));
        list.add(new ManagerModel(4, "Kiet", "sldfje", "0987123456", "TP.HCM" , 50000));


        recyclerView = fragmentLayout.findViewById(R.id.rvManager);

        layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(RecyclerView.VERTICAL);

        managerAdapter = new ManagerAdapter(getActivity(), list, R.layout.cardview_admin_manager_layout);


        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(managerAdapter);



        return fragmentLayout;
    }
}
