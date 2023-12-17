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
import com.example.mobileproject.activity.admin.InfomationMotelRoomDeleteActivity;
import com.example.mobileproject.api.admin.ApiServiceMinh;
import com.example.mobileproject.datamodel.PhongTro;
import com.example.mobileproject.datamodel.YeuCauXoaPhong;
import com.example.mobileproject.recycerviewadapter.admin.PendingMotelRoomAdapter;

import java.util.LinkedList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MotelRoomDeleteFragment extends AbstractFragment {

    private RecyclerView rcvMotelRoomDelete;
    private PendingMotelRoomAdapter adapter;
    private LinearLayoutManager layoutManager;
    private List<YeuCauXoaPhong> list;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentLayout = null;
        fragmentLayout = inflater.inflate(R.layout.fragment_admin_pending_motel_room_delete_layout, container, false);


        anhXa(fragmentLayout);
        
        getDataFromAPI();
        
        batSuKien();

        return fragmentLayout;
    }

    @Override
    public void onResume() {
        super.onResume();
        getDataFromAPI();
    }

    private void getDataFromAPI() {
        ApiServiceMinh.apiService.layDanhSachYeuCauXoaPhong().enqueue(new Callback<List<YeuCauXoaPhong>>() {
            @Override
            public void onResponse(Call<List<YeuCauXoaPhong>> call, Response<List<YeuCauXoaPhong>> response) {
                if (response.code() == 200){
                    if (response.body()!=null) {
                        list.clear();
                        list.addAll(response.body());
                        adapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<YeuCauXoaPhong>> call, Throwable t) {

            }
        });
    }

    private void batSuKien() {
        adapter.setOnClick(new PendingMotelRoomAdapter.OnClick() {
            @Override
            public void onClickItemListener(int position, View v) {
                Intent intent = new Intent(getActivity(), InfomationMotelRoomDeleteActivity.class);
                intent.putExtra("id", list.get(position).getId());

                startActivity(intent);
            }
        });
    }

    private void anhXa(View fragmentLayout) {
        rcvMotelRoomDelete = fragmentLayout.findViewById(R.id.rcvMotelRoonDelete);
        list = new LinkedList<>();
        adapter = new PendingMotelRoomAdapter(getActivity(), list, R.layout.cardview_admin_pending_motel_room_layout);
        layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        rcvMotelRoomDelete.setLayoutManager(layoutManager);
        rcvMotelRoomDelete.setAdapter(adapter);
    }


}
