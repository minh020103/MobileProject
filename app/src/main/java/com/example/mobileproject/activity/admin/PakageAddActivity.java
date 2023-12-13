package com.example.mobileproject.activity.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.mobileproject.R;
import com.example.mobileproject.api.admin.ApiServiceKiet;
import com.example.mobileproject.datamodel.Goi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PakageAddActivity extends AppCompatActivity {
    ImageView imgBackGoiDVThem;
    EditText edtThoiHanGoiDVThem;
    EditText edtSoLuongPhongGoiDVThem;
    EditText edtGiaGoiDVThem;
    Button btnGoiDVThem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pakage_add_layout);

        imgBackGoiDVThem = findViewById(R.id.imgBackGoiDVThem);
        edtThoiHanGoiDVThem = findViewById(R.id.edtThoiHanGoiDVThem);
        edtSoLuongPhongGoiDVThem = findViewById(R.id.edtSoLuongPhongGoiDVThem);
        edtGiaGoiDVThem = findViewById(R.id.edtGiaGoiDVThem);
        btnGoiDVThem = findViewById(R.id.btnGoiDVThem);

        btnGoiDVThem.setEnabled(false);

        if (edtThoiHanGoiDVThem.getText() != null && edtSoLuongPhongGoiDVThem.getText() != null && edtGiaGoiDVThem.getText() !=null)
        {
            btnGoiDVThem.setEnabled(true);
        }

        btnGoiDVThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int thoiHan = Integer.parseInt(edtThoiHanGoiDVThem.getText()+"");
                int soLuong = Integer.parseInt(edtSoLuongPhongGoiDVThem.getText()+"");
                int gia = Integer.parseInt(edtGiaGoiDVThem.getText()+"");

                if (thoiHan >= 0 && soLuong >=0 && gia >=0)
                {
                    openDialogConfirmAddPakage(thoiHan, soLuong, gia);
                }
                else
                {
                    new AlertDialog.Builder(getApplicationContext()).setMessage("Lỗi").setCancelable(false).setNegativeButton("Quay lại",null).show();
                }

            }
        });

        imgBackGoiDVThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    private void AddPakageAPI(int thoiHan, int soLuongPhongToiDa, int gia)
    {
        ApiServiceKiet.apiServiceKiet.addPakage(thoiHan,soLuongPhongToiDa,gia).enqueue(new Callback<Goi>() {
            @Override
            public void onResponse(Call<Goi> call, Response<Goi> response) {

            }
            @Override
            public void onFailure(Call<Goi> call, Throwable t) {

            }
        });
    }

    private void openDialogConfirmAddPakage(int i1, int i2, int i3)
    {
        new AlertDialog.Builder(this).setMessage("Xác nhận thêm gói dịch vụ mới ?").setCancelable(false).setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                AddPakageAPI(i1, i2, i3);
            }
        }).setNegativeButton("Hủy",null).show();
    }
}