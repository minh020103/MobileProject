package com.example.mobileproject.fragment.admin.pending;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileproject.R;
import com.example.mobileproject.activity.admin.InfomationPackageRegisterActivity;
import com.example.mobileproject.api.admin.ApiServiceMinh;
import com.example.mobileproject.datamodel.YeuCauDangKyGoi;
import com.example.mobileproject.recycerviewadapter.admin.PendingPackageAdapter;
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

public class PackageRegisterFragment extends AbstractFragment {

    private RecyclerView rcvPandingPackage;
    private PendingPackageAdapter adapter;
    private LinearLayoutManager layoutManager;
    private List<YeuCauDangKyGoi> list;
    private ProgressBar load;

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentLayout = null;
        fragmentLayout = inflater.inflate(R.layout.fragment_admin_pending_package_register_layout, container, false);

        anhXa(fragmentLayout);




        clickItem();

        return fragmentLayout;
    }

    @Override
    public void onResume() {
        super.onResume();
        databaseReference.child("notification_admin").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                getDataFromAPI();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void getDataFromAPI() {
        load.setVisibility(View.VISIBLE);
        ApiServiceMinh.apiService.layTatCaYeuCauDangKyGoi().enqueue(new Callback<List<YeuCauDangKyGoi>>() {
            @Override
            public void onResponse(Call<List<YeuCauDangKyGoi>> call, Response<List<YeuCauDangKyGoi>> response) {
                if (response.code() == 200) {
                    list.clear();
                    list.addAll(response.body());
                    adapter.notifyDataSetChanged();
                    load.setVisibility(View.GONE);
                }

            }

            @Override
            public void onFailure(Call<List<YeuCauDangKyGoi>> call, Throwable t) {

            }
        });
    }

    private void clickItem() {
        adapter.setOnClick(new PendingPackageAdapter.OnClick() {
            @Override
            public void onClickItemListenner(int position, View v) {
                Intent intent = new Intent(getActivity(), InfomationPackageRegisterActivity.class);
                intent.putExtra("id", list.get(position).getId());
                startActivity(intent);
            }
        });
    }

    private void anhXa(View fragmentLayout) {
        load = fragmentLayout.findViewById(R.id.idLoad);
        list = new LinkedList<>();
        rcvPandingPackage = fragmentLayout.findViewById(R.id.rcvPendingPackage);
        adapter = new PendingPackageAdapter(getActivity(), list, R.layout.cardview_admin_pending_package_register_layout);
        layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        rcvPandingPackage.setLayoutManager(layoutManager);
        rcvPandingPackage.setAdapter(adapter);
    }
}
