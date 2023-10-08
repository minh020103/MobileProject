package com.example.mobileproject.fragment.admin.pending;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileproject.R;
import com.example.mobileproject.activity.admin.InfomationMotelRoomDeleteActivity;
import com.example.mobileproject.datamodel.PhongTro;
import com.example.mobileproject.recycerviewadapter.admin.PendingMotelRoomAdapter;

import java.util.LinkedList;
import java.util.List;

public class MotelRoomDeleteFragment extends AbstractFragment {

    private RecyclerView rcvMotelRoomDelete;
    private PendingMotelRoomAdapter adapter;
    private LinearLayoutManager layoutManager;
    private List<PhongTro> list;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentLayout = null;
        fragmentLayout = inflater.inflate(R.layout.fragment_admin_pending_motel_room_delete_layout, container, false);

        anhXa(fragmentLayout);

        batSuKien();

        return fragmentLayout;
    }

    private void batSuKien() {
        adapter.setOnClick(new PendingMotelRoomAdapter.OnClick() {
            @Override
            public void onClickItemListener(int position, View v) {
                Intent intent = new Intent(getActivity(), InfomationMotelRoomDeleteActivity.class);
                startActivity(intent);
            }
        });
    }

    private void anhXa(View fragmentLayout) {
        rcvMotelRoomDelete = fragmentLayout.findViewById(R.id.rcvMotelRoonDelete);
        list = new LinkedList<>();
        list.add(new PhongTro(1));
        list.add(new PhongTro(1));
        list.add(new PhongTro(1));
        list.add(new PhongTro(1));
        list.add(new PhongTro(1));
        list.add(new PhongTro(1));
        list.add(new PhongTro(1));
        list.add(new PhongTro(1));
        list.add(new PhongTro(1));
        adapter = new PendingMotelRoomAdapter(getActivity(), list, R.layout.cardview_admin_pending_motel_room_layout);
        layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        rcvMotelRoomDelete.setLayoutManager(layoutManager);
        rcvMotelRoomDelete.setAdapter(adapter);
    }


}
