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

import java.io.File;
import java.io.IOException;

public class AddUtilitiesActivity extends AppCompatActivity {
    AppCompatImageView ic_back;
    ImageView imageTienIch;
    EditText edtTenTienIch;
    Button btnThemTienIch;

    private Uri mUri;
    public static final String TAG = Manifest.class.getName();
    private static final int MY_REQUEST_CODE = 10;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_utilities);
        anhXa();

    }
    private boolean kiemTra(EditText edtTenTienIch, Uri mUri){
        if(mUri!=null&&!edtTenTienIch.getText().toString().isEmpty()){
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
        imageTienIch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickRequestPermission();
            }
        });
        btnThemTienIch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(kiemTra(edtTenTienIch,mUri)){
                    themTienIch(edtTenTienIch,mUri);
                }else{
                    thongBao("Thiếu Ảnh Hoặc Tên Tiện Ích");
                }
            }
        });

    }
    private void themTienIch(EditText edtTenTienIch, Uri mUri){
        String strRealPath = RealPathUtil.getRealPath(this,mUri);
        File file = new File(strRealPath);
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
                            imageTienIch.setImageBitmap(bitmap);
                        }catch (IOException e){
                            e.printStackTrace();
                        }
                    }
                }
            }
    );
    private void thongBao(String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
        builder.setMessage(message).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
    }

    private void anhXa(){
        ic_back = findViewById(R.id.icon_back);
        imageTienIch = findViewById(R.id.imgTienIch);
        edtTenTienIch = findViewById(R.id.editTenTienIch);
        btnThemTienIch.findViewById(R.id.btnThemTienIch);
    }
}
