package com.example.mobileproject.fragment.admin.pending;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileproject.R;
import com.example.mobileproject.activity.admin.InfomationConfirmMotelRoomOwner;
import com.example.mobileproject.api.ApiFCMService;
import com.example.mobileproject.api.Const;
import com.example.mobileproject.api.admin.ApiServiceMinh;
import com.example.mobileproject.datamodel.YeuCauXacThuc;
import com.example.mobileproject.datamodel.fcm.PushNotification;
import com.example.mobileproject.recycerviewadapter.admin.PendingOwnerAccountAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
    private ProgressBar load;

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentLayout = null;
        fragmentLayout = inflater.inflate(R.layout.fragment_admin_pending_account_owner_layout, container, false);


        anhXa(fragmentLayout);
        suLi();
        databaseReference.child("notification_admin").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                callAPI();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




        adapter.setOnClickItemListener(new PendingOwnerAccountAdapter.OnClickItemListener() {
            @Override
            public void onClick(int position, View view) {
                Intent intent = new Intent(getActivity(), InfomationConfirmMotelRoomOwner.class);
                intent.putExtra("idChuTro", listYeuCauXacThuc.get(position).getChuTro().getId());
                Log.d("TAG", "idChuTro: "+listYeuCauXacThuc.get(position).getChuTro().getId());
                startActivity(intent);
            }
        });


        return fragmentLayout;
    }

    @Override
    public void onResume() {
        super.onResume();


    }

    private void suLi() {
        adapter = new PendingOwnerAccountAdapter(getActivity(), R.layout.cardview_admin_pending_owner_account_layout, listYeuCauXacThuc);
        layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        rcvAccountOwner.setLayoutManager(layoutManager);
        rcvAccountOwner.setAdapter(adapter);

    }

    private void anhXa(View fragmentLayout) {
        load = fragmentLayout.findViewById(R.id.idLoad);
        rcvAccountOwner = fragmentLayout.findViewById(R.id.rcvAccountOwner);
        listYeuCauXacThuc = new LinkedList<>();
    }


    private void callAPI() {
        load.setVisibility(View.VISIBLE);
        ApiServiceMinh.apiService.layTatCaYeuCauXacThuc().enqueue(new Callback<List<YeuCauXacThuc>>() {
            @Override
            public void onResponse(Call<List<YeuCauXacThuc>> call, Response<List<YeuCauXacThuc>> response) {
                if (response.code() == 200) {
                    listYeuCauXacThuc.clear();
                    listYeuCauXacThuc.addAll(response.body());
                    adapter.notifyDataSetChanged();
                    load.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<List<YeuCauXacThuc>> call, Throwable t) {

            }
        });


    }
}
