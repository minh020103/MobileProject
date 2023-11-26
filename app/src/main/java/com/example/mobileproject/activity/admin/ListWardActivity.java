package com.example.mobileproject.activity.admin;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobileproject.R;
import com.example.mobileproject.api.admin.ApiServiceDung;
import com.example.mobileproject.datamodel.Phuong;
import com.example.mobileproject.datamodel.Quan;
import com.example.mobileproject.recycerviewadapter.admin.WardAdapter;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListWardActivity extends AppCompatActivity {

    private RecyclerView rcvWard;
    TextView text_name_quan;
    ImageView imgBack, img_add;
    private WardAdapter adapter;
    private List<Phuong> list;
    int idQuan, idPhuong;
    String nameQuan;
    Integer trangThai;
    BottomSheetDialog bottomSheetDialogEdit;

    private LinearLayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_list_ward_layout);
        anhXa();
        danhSachPhuong();
        Intent intent = getIntent();
        idQuan = intent.getIntExtra("idQuan",0);
        nameQuan = intent.getStringExtra("nameQuan");
        text_name_quan.setText(nameQuan);
        batSuKienClickItem();
    }

    private void batSuKienClickItem() {
        adapter.setMyOnClickListenner(new WardAdapter.MyOnClickListenner() {
            @Override
            public void onClickItemListener(int position, View v) {
                openButtomSheetEdit(position);
            }
        });
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        img_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openButtomSheetAdd();
            }
        });
    }

    private void openButtomSheetEdit(int positiom) {
        Phuong phuong = list.get(positiom);
        View view = getLayoutInflater().inflate(R.layout.admin_buttom_sheet_layout, null, false);
        bottomSheetDialogEdit = new BottomSheetDialog(this, R.style.BottomSheetDialogTheme);
        bottomSheetDialogEdit.setContentView(view);
        EditText editText = view.findViewById(R.id.ten_phuong);
        Button button = view.findViewById(R.id.btnPhuong);
        editText.setText(phuong.getTenPhuong());
        trangThai = phuong.getTrangThai();
        idPhuong = phuong.getId();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenPhuong = editText.getText().toString();
                RequestBody namePhuong = RequestBody.create(MediaType.parse("multipart/form-data"),tenPhuong);
                //Log.d("TAG", "onClick: "+tenPhuong + trangThai);
                ApiServiceDung.apiServiceDung.editPhuong(idPhuong,namePhuong,idQuan,trangThai).enqueue(new Callback<Integer>() {
                    @Override
                    public void onResponse(Call<Integer> call, Response<Integer> response) {
                        thongBao("Sửa Phường Thành Công");
                    }

                    @Override
                    public void onFailure(Call<Integer> call, Throwable t) {
                        thongBao("Lỗi Hệ Thống ....");
                    }
                });
            }
        });

        bottomSheetDialogEdit.show();
    }
    private void openButtomSheetAdd() {
        View view = getLayoutInflater().inflate(R.layout.admin_buttom_sheet_layout, null, false);
        bottomSheetDialogEdit = new BottomSheetDialog(this, R.style.BottomSheetDialogTheme);
        bottomSheetDialogEdit.setContentView(view);
        EditText editText = view.findViewById(R.id.ten_phuong);
        Button button = view.findViewById(R.id.btnPhuong);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenPhuong = editText.getText().toString();
                RequestBody namePhuong = RequestBody.create(MediaType.parse("multipart/form-data"),tenPhuong);
                //Log.d("TAG", "onClick: "+tenPhuong);
                ApiServiceDung.apiServiceDung.themphuong(namePhuong,idQuan).enqueue(new Callback<Phuong>() {
                    @Override
                    public void onResponse(Call<Phuong> call, Response<Phuong> response) {
                        thongBao("Thêm Phường Mới Vào " + nameQuan +" Thành Công");
                    }

                    @Override
                    public void onFailure(Call<Phuong> call, Throwable t) {
                        thongBao("Lỗi Hệ Thống ....");
                    }
                });
            }
        });

        bottomSheetDialogEdit.show();
    }

    private void anhXa() {
        rcvWard = findViewById(R.id.rcvWard);
        imgBack = findViewById(R.id.img_Back);
        img_add = findViewById(R.id.img_add);
        text_name_quan = findViewById(R.id.text_name_quan);
    }

    private void danhSachPhuong(){
        list = new ArrayList<>();
        adapter = new WardAdapter(this, list, R.layout.cardview_admin_ward_layout);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        rcvWard.setLayoutManager(layoutManager);
        rcvWard.setAdapter(adapter);
        ApiServiceDung.apiServiceDung.layTatCaPhuongTheoQuan(1).enqueue(new Callback<ArrayList<Phuong>>() {
            @Override
            public void onResponse(Call<ArrayList<Phuong>> call, Response<ArrayList<Phuong>> response) {
                list.addAll(response.body());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ArrayList<Phuong>> call, Throwable t) {

            }
        });
    }

    private void thongBao(String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                onResume();
                bottomSheetDialogEdit.dismiss();
            }
        });
        builder.create();
        builder.show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        anhXa();
        danhSachPhuong();
        Intent intent = getIntent();
        idQuan = intent.getIntExtra("idQuan",0);
        nameQuan = intent.getStringExtra("nameQuan");
        text_name_quan.setText(nameQuan);
        batSuKienClickItem();
    }
}