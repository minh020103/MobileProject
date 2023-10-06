package com.example.mobileproject.fragment.admin;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileproject.R;
import com.example.mobileproject.activity.admin.HostDetailActivity;
import com.example.mobileproject.adapter.admin.HostAdapter;
import com.example.mobileproject.apiKiet.ApiServiceKiet;
import com.example.mobileproject.model.HostModel;
import com.example.mobileproject.until.AppUtil;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ManagementFragment extends AbstractFragment{
        /* Hard code */
    RecyclerView recyclerView;
    List<HostModel> list;
    HostAdapter hostAdapter;
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

        recyclerView.setAdapter(hostAdapter);

        list = new ArrayList<>();

        ListManagerApi();

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtSearch.getText() != null)
                {
                    String key = String.valueOf(edtSearch.getText());
                    HostByNameApi(key);
                    HostByPhoneApi(key);
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
        ApiServiceKiet.apiServiceKiet.getListHostAPI().enqueue(new Callback<List<HostModel>>() {
            @Override
            public void onResponse(Call<List<HostModel>> call, Response<List<HostModel>> response) {
                Log.d("tinnhan", "thanh cong");
                //List<HostModel> list = response.body();
                list = response.body();
                hostAdapter = new HostAdapter(getActivity(),list, R.layout.cardview_admin_manager_layout);
                recyclerView.setAdapter(hostAdapter);



                hostAdapter.setOnClickItemListener(new HostAdapter.OnClickItemListener() {
                    @Override
                    public void onClickItem(int position, View v) {
                        Log.d("TAG", list.get(position)+"");
                        AppUtil.Id = GetPositionId(list.get(position).getId());
                        nextActivity();
                    }
                });


            }

            @Override
            public void onFailure(Call<List<HostModel>> call, Throwable t) {
                Log.d("tinnhan", "that bai");
            }
        });

    }

    private void HostByNameApi(String key)
    {
        ApiServiceKiet.apiServiceKiet.getHostByNameAPI(key).enqueue(new Callback<List<HostModel>>() {
            @Override
            public void onResponse(Call<List<HostModel>> call, Response<List<HostModel>> response) {
                Log.d("tinnhan", "thanh cong");
                //List<HostModel> list = response.body();
                list = response.body();
                hostAdapter = new HostAdapter(getActivity(),list, R.layout.cardview_admin_manager_layout);
                recyclerView.setAdapter(hostAdapter);



                hostAdapter.setOnClickItemListener(new HostAdapter.OnClickItemListener() {
                    @Override
                    public void onClickItem(int position, View v) {
                        Log.d("TAG", list.get(position)+"");
                        AppUtil.Id = GetPositionId(list.get(position).getId());
                        nextActivity();
                    }
                });


            }

            @Override
            public void onFailure(Call<List<HostModel>> call, Throwable t) {
                Log.d("tinnhan", "that bai");
            }
        });

    }

    private void HostByPhoneApi(String key)
    {
        ApiServiceKiet.apiServiceKiet.getHostByPhoneAPI(key).enqueue(new Callback<List<HostModel>>() {
            @Override
            public void onResponse(Call<List<HostModel>> call, Response<List<HostModel>> response) {
                Log.d("tinnhan", "thanh cong");
                //List<HostModel> list = response.body();
                list = response.body();
                hostAdapter = new HostAdapter(getActivity(),list, R.layout.cardview_admin_manager_layout);
                recyclerView.setAdapter(hostAdapter);



                hostAdapter.setOnClickItemListener(new HostAdapter.OnClickItemListener() {
                    @Override
                    public void onClickItem(int position, View v) {
                        Log.d("TAG", list.get(position)+"");
                        AppUtil.Id = GetPositionId(list.get(position).getId());
                        nextActivity();
                    }
                });


            }

            @Override
            public void onFailure(Call<List<HostModel>> call, Throwable t) {
                Log.d("tinnhan", "that bai");
            }
        });

    }

    private int GetPositionId(int Id)
    {
        return Id;
    }


    private void nextActivity()
    {
        Intent intent = new Intent(getActivity(), HostDetailActivity.class);
        startActivity(intent);
    }
}
