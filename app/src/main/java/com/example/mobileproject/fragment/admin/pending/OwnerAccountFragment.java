package com.example.mobileproject.fragment.admin.pending;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileproject.R;
import com.example.mobileproject.activity.admin.InfomationConfirmMotelRoomOwner;
import com.example.mobileproject.api.admin.ApiServiceMinh;
import com.example.mobileproject.datamodel.YeuCauXacThuc;
import com.example.mobileproject.recycerviewadapter.admin.PendingOwnerAccountAdapter;
import com.example.mobileproject.datamodel.ChuTro;

import java.util.LinkedList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OwnerAccountFragment extends AbstractFragment {
    private RecyclerView rcvAccountOwner;
    private PendingOwnerAccountAdapter adapter;
    private LinearLayoutManager layoutManager;
    private List<YeuCauXacThuc> listYeuCauXacThuc;
//    Handler handler;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentLayout = null;
        fragmentLayout = inflater.inflate(R.layout.fragment_admin_pending_account_owner_layout, container, false);


        anhXa(fragmentLayout);
        suLi();


        return fragmentLayout;
    }

    @Override
    public void onResume() {
        super.onResume();


        callAPI();


//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
                callAPI();
//                handler.postDelayed(this, 3000);
//            }
//        }, 3000);


        adapter.setOnClickItemListener(new PendingOwnerAccountAdapter.OnClickItemListener() {
            @Override
            public void onClick(int position, View view) {
                Intent intent = new Intent(getActivity(), InfomationConfirmMotelRoomOwner.class);
                intent.putExtra("idChuTro", listYeuCauXacThuc.get(position).getChuTro().getId());
                Log.d("TAG", "idChuTro: "+listYeuCauXacThuc.get(position).getChuTro().getId());
                startActivity(intent);
            }
        });
    }

    private void suLi() {
        adapter = new PendingOwnerAccountAdapter(getActivity(), R.layout.cardview_admin_pending_owner_account_layout, listYeuCauXacThuc);
        layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        rcvAccountOwner.setLayoutManager(layoutManager);
        rcvAccountOwner.setAdapter(adapter);

    }

    private void anhXa(View fragmentLayout) {
//        handler = new Handler();
        rcvAccountOwner = fragmentLayout.findViewById(R.id.rcvAccountOwner);
        listYeuCauXacThuc = new LinkedList<>();
    }


    private void callAPI() {
        ApiServiceMinh.apiService.layTatCaYeuCauXacThuc().enqueue(new Callback<List<YeuCauXacThuc>>() {
            @Override
            public void onResponse(Call<List<YeuCauXacThuc>> call, Response<List<YeuCauXacThuc>> response) {
                if (response.code() == 200) {
                    listYeuCauXacThuc.clear();
                    listYeuCauXacThuc.addAll(response.body());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<YeuCauXacThuc>> call, Throwable t) {

            }
        });


    }
}
