package com.example.mobileproject.fragment.admin;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileproject.R;
import com.example.mobileproject.adapter.admin.ManagerAdapter;
import com.example.mobileproject.apiKiet.ApiServiceKiet;
import com.example.mobileproject.model.ManagerModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ManagementFragment extends AbstractFragment{
        /* Hard code */
    RecyclerView recyclerView;
    List<ManagerModel> list;
    ManagerAdapter managerAdapter;
    LinearLayoutManager layoutManager;

    EditText edtSearch;
    Button btnSearch;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentLayout = null;
        fragmentLayout = inflater.inflate(R.layout.fragment_admin_management_layout, container, false);



        /* Hard code */
        /// code
//        list = new ArrayList<>();
//        list.add(new ManagerModel(1, "Kiet", "sldfje", "0987123456", "TP.HCM" , 50000));
//        list.add(new ManagerModel(2, "Kiet", "sldfje", "0987123456", "TP.HCM" , 50000));
//        list.add(new ManagerModel(3, "Kiet", "sldfje", "0987123456", "TP.HCM" , 50000));
//        list.add(new ManagerModel(4, "Kiet", "sldfje", "0987123456", "TP.HCM" , 50000));
//
//
//        recyclerView = fragmentLayout.findViewById(R.id.rvManager);
//
//        layoutManager = new LinearLayoutManager(getActivity());
//        layoutManager.setOrientation(RecyclerView.VERTICAL);
//
//        managerAdapter = new ManagerAdapter(getActivity(), list, R.layout.cardview_admin_manager_layout);
//
//
//        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setAdapter(managerAdapter);

        edtSearch = fragmentLayout.findViewById(R.id.edt_search);
        btnSearch = fragmentLayout.findViewById(R.id.btn_search);

        recyclerView = fragmentLayout.findViewById(R.id.rvManager);
        layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(managerAdapter);

        list = new ArrayList<>();

        ListManagerApi();

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtSearch.getText() != null)
                {
                    String key = String.valueOf(edtSearch.getText());
                    ManagerByNameApi(key);
                    ManagerByAreaAPI(key);
                }
                else
                {
                    ListManagerApi();
                }
            }
        });



        return fragmentLayout;
    }

    private void ListManagerApi()
    {
        ApiServiceKiet.apiServiceKiet.getListManagerAPI().enqueue(new Callback<List<ManagerModel>>() {
            @Override
            public void onResponse(Call<List<ManagerModel>> call, Response<List<ManagerModel>> response) {
                Log.d("tinnhan", "thanh cong");
                List<ManagerModel> list1 = response.body();
                list = response.body();
                managerAdapter = new ManagerAdapter(getActivity(),list, R.layout.cardview_admin_manager_layout);
                recyclerView.setAdapter(managerAdapter);



                managerAdapter.setOnClickItemListene(new ManagerAdapter.OnClickItemListener() {
                    @Override
                    public void onClickItem(int position, View v) {
                        Log.d("TAG", list.get(position)+"");
                    }
                });


            }

            @Override
            public void onFailure(Call<List<ManagerModel>> call, Throwable t) {
                Log.d("tinnhan", "that bai");
            }
        });

    }

    private void ManagerByNameApi(String key)
    {
        ApiServiceKiet.apiServiceKiet.getManagerByIdAPI(key).enqueue(new Callback<List<ManagerModel>>() {
            @Override
            public void onResponse(Call<List<ManagerModel>> call, Response<List<ManagerModel>> response) {
                Log.d("tinnhan", "thanh cong");
                List<ManagerModel> list1 = response.body();
                list = response.body();
                managerAdapter = new ManagerAdapter(getActivity(),list, R.layout.cardview_admin_manager_layout);
                recyclerView.setAdapter(managerAdapter);



                managerAdapter.setOnClickItemListene(new ManagerAdapter.OnClickItemListener() {
                    @Override
                    public void onClickItem(int position, View v) {
                        Log.d("TAG", list.get(position)+"");
                    }
                });


            }

            @Override
            public void onFailure(Call<List<ManagerModel>> call, Throwable t) {
                Log.d("tinnhan", "that bai");
            }
        });

    }

    private void ManagerByAreaAPI(String key)
    {
        ApiServiceKiet.apiServiceKiet.getManagerByAreaAPI(key).enqueue(new Callback<List<ManagerModel>>() {
            @Override
            public void onResponse(Call<List<ManagerModel>> call, Response<List<ManagerModel>> response) {
                Log.d("tinnhan", "thanh cong");
                List<ManagerModel> list1 = response.body();
                list = response.body();
                managerAdapter = new ManagerAdapter(getActivity(),list, R.layout.cardview_admin_manager_layout);
                recyclerView.setAdapter(managerAdapter);



                managerAdapter.setOnClickItemListene(new ManagerAdapter.OnClickItemListener() {
                    @Override
                    public void onClickItem(int position, View v) {
                        Log.d("TAG", list.get(position)+"");
                    }
                });


            }

            @Override
            public void onFailure(Call<List<ManagerModel>> call, Throwable t) {
                Log.d("tinnhan", "that bai");
            }
        });

    }
}
