package com.example.mobileproject.activity.admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.mobileproject.R;
import com.example.mobileproject.datamodel.Phuong;
import com.example.mobileproject.recycerviewadapter.admin.WardAdapter;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.LinkedList;
import java.util.List;

public class ListWardActivity extends AppCompatActivity {

    private RecyclerView rcvWard;
    private WardAdapter adapter;
    private List<Phuong> list;
    private LinearLayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_list_ward_layout);


        anhXa();

        batSuKienClickItem();
    }

    private void batSuKienClickItem() {
        adapter.setMyOnClickListenner(new WardAdapter.MyOnClickListenner() {
            @Override
            public void onClickItemListener(int position, View v) {
                openButtomSheet();
            }
        });
    }

    private void openButtomSheet() {
        View diaLog = getLayoutInflater().inflate(R.layout.admin_buttom_sheet_layout, null);
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(diaLog);
        bottomSheetDialog.show();

    }

    private void anhXa() {
        rcvWard =findViewById(R.id.rcvWard);
        list = new LinkedList<>();
        list.add(new Phuong());
        list.add(new Phuong());
        list.add(new Phuong());
        list.add(new Phuong());
        adapter = new WardAdapter(this, list, R.layout.cardview_admin_ward_layout);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        rcvWard.setLayoutManager(layoutManager);
        rcvWard.setAdapter(adapter);
    }
}