package com.example.mobileproject.activity.admin;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import com.example.mobileproject.R;
import com.example.mobileproject.RealPathUtil;
import com.example.mobileproject.api.admin.ApiServiceDung;
import com.example.mobileproject.api.admin.ApiServiceNghiem;
import com.example.mobileproject.datamodel.Quan;
import com.example.mobileproject.datamodel.TienIch;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddDistrictActivity extends AppCompatActivity {
    AppCompatImageView ic_back;
    ImageView imgQuan;
    EditText editTenQuan;
    Button btnThemQuan;

    private Uri mUri;
    public static final String TAG = Manifest.class.getName();
    private static final int MY_REQUEST_CODE = 10;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_district);
        anhXa();
        setSuKien();
    }
    private boolean kiemTra(EditText editTenQuan, Uri mUri){
        if(mUri!=null&&!editTenQuan.getText().toString().isEmpty()){
            return true;
        }
        return false;
    }
    private void setSuKien(){
        ic_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        imgQuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickRequestPermission();
            }
        });
        btnThemQuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(kiemTra(editTenQuan,mUri)){
                    themQuan(editTenQuan,mUri);

                }else{
                    thongBao("Thiếu Ảnh Hoặc Tên Tiện Ích");
                }
            }
        });

    }
    private void themQuan(EditText edtQuan, Uri mUri){
        String strRealPath = RealPathUtil.getRealPath(this,mUri);
        File file = new File(strRealPath);
        String ten = edtQuan.getText().toString();
        RequestBody tenQuan = RequestBody.create(MediaType.parse("multipart/form-data"),ten);
        RequestBody requestBodyImage = RequestBody.create(MediaType.parse("multipart/form-data"),file);
        MultipartBody.Part mulPart = MultipartBody.Part.createFormData("hinh",file.getName(),requestBodyImage);
        Call<Quan> call = ApiServiceDung.apiServiceDung.themQuan(tenQuan,mulPart);
        call.enqueue(new Callback<Quan>() {
            @Override
            public void onResponse(Call<Quan> call, Response<Quan> response) {
                thongBao("Them Tien Ich Thanh Cong");
            }

            @Override
            public void onFailure(Call<Quan> call, Throwable t) {
                thongBao("That Bai");
            }
        });
    }

    private void onClickRequestPermission(){
        if(Build.VERSION.SDK_INT<Build.VERSION_CODES.M){
            openGallery();
            return;
        }
        if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED){
            openGallery();
        }else{
            String[] permission = {Manifest.permission.READ_EXTERNAL_STORAGE};
            requestPermissions(permission,MY_REQUEST_CODE);
        }
    }
    private void openGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        mActivityResultLauncher.launch(Intent.createChooser(intent,"Select Picture"));
    }
    private ActivityResultLauncher<Intent> mActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    Log.e(TAG,"onActivityResult");
                    if(result.getResultCode() == Activity.RESULT_OK){
                        Intent data = result.getData();
                        if(data==null){
                            return;
                        }
                        Uri uri = data.getData();
                        mUri = uri;
                        try{
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
                            imgQuan.setImageBitmap(bitmap);
                        }catch (IOException e){
                            e.printStackTrace();
                        }
                    }
                }
            }
    );
    private void thongBao(String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(AddDistrictActivity.this);
        builder.setMessage(message).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        builder.create();
        builder.show();
    }

    private void anhXa(){
        ic_back = findViewById(R.id.icon_back);
        imgQuan = findViewById(R.id.imgQuan);
        editTenQuan = findViewById(R.id.editTenQuan);
        btnThemQuan=findViewById(R.id.btnThemQuan);
    }
}
